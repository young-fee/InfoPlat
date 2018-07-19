<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>连连看</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
    <script type="text/javascript">
        // canvas obj
        var _canvas = null;
        // context obj
        var _ctx = null;
        // face image obj
        var _img_face = null;

        // face img size
        var _img_face_m = 0;
        var _img_face_n = 0;
        var _img_face_src_width = 0;
        var _img_face_src_height = 0;
        var _img_face_width = 0;
        var _img_face_height = 0;
        var _img_face_radius = 0;

        // board size
        var _board_m = 0;
        var _board_n = 0;
        var _boardsize = 0;
        var _board_m_offset = 0;
        var _board_n_offset = 0;

        // board obj
        var _board = null;

        // canvas size
        var _width = 0;
        var _height = 0;

        // first selected face
        var _first_p = null;
        // last moveout face
        var _old_p = null;

        // animation flag
        var _is_play_animation = false;

        // board state flag
        // 0: start
        // 1: pause
        // 2: stop
        var _board_state = 0;

        // current time
        var _current_time = null;
        var _pause_time = null;
        var _timer_time = null;

        // entry point
        window.onload = function () {
            // face img resource size
            _img_face_m = 13;
            _img_face_n = 4;
            _img_face_src_width = 60;
            _img_face_src_height = 60;
            _img_face_width = 60;
            _img_face_height = 60;
            _img_face_radius = 10;

            //// calc canvas size
            //_width = (16 + 2) * _img_face_width;
            //_height = (9 + 2) * _img_face_height;

            // get canvas obj
            _canvas = document.getElementById('canvas');
            if (_canvas != null && _canvas.getContext != null) {
                //_canvas.width = _width;
                //_canvas.height = _height;
                _canvas.style.backgroundImage = 'url(' + img_bg + ')';
                _ctx = _canvas.getContext("2d");
                _ctx.save();

                _canvas.onmousemove = OnCanvas_MouseMove;
                _canvas.onmousedown = OnCanvas_Click;
            }

            // load face image to image obj
            _img_face = new Image();
            _img_face.onload = function () {
                // new game
                btnNewGame_Click();
            }
            _img_face.src = img_src;
        }

        // event
        function btnNewGame_Click() {
            if (_is_play_animation) return;
            _board_state = 0;
            document.getElementById('btnOrder').value = '暂停查看排名';

            var boardsize = parseInt(document.getElementById('boardsize').value);
            if (boardsize != _boardsize) {
                _boardsize = boardsize;
                _first_p = null;
                _old_p = null;

                switch (boardsize) {
                    case 1:
                        _board_m = 8;
                        _board_n = 6;
                        break;
                    case 3:
                        _board_m = 16;
                        _board_n = 9;
                        break;
                    case 2:
                    default:
                        _board_m = 12;
                        _board_n = 8;
                        break;
                }
                _width = (_board_m + 2) * _img_face_width;
                _height = (_board_n + 2) * _img_face_height;
                _board_m_offset = (_width - (_board_m + 2) * _img_face_width) / 2;
                _board_n_offset = (_height - (_board_n + 2) * _img_face_height) / 2;

                if (_ctx != null) {
                    _canvas.width = _width;
                    _canvas.height = _height;
                    _ctx.restore();
                    _ctx.save();
                    _ctx.clearRect(0, 0, _width, _height);
                    _ctx.translate(_board_m_offset, _board_n_offset);
                }
                _board = null;
                _board = new Board();
                _board.Init(_board_m, _board_n, _img_face_m * _img_face_n);
            }

            _board.NewGame();

            if (_ctx != null) {
                draw_board();
            }

            if (_timer_time != null) {
                clearInterval(_timer_time);
                _current_time = null;
                _timer_time = null;
            }

            _current_time = (new Date()).getTime();
            draw_time(calc_time());
            _timer_time = setInterval(function () {
                draw_time(calc_time());
            }, 500);
        }

        function btnHint_Click() {
            if (_is_play_animation) return;
            if (_board_state > 0) return;

            if (_board != null) {
                var path = new PATH();
                if (_board.GetHint(path) && _ctx != null) {
                    // draw path
                    draw_face_animation(path.Start.X, path.Start.Y, 'rgba(255,40,40, 1.0)');
                    draw_face_animation(path.End.X, path.End.Y, 'rgba(255,40,40, 1.0)');
                    draw_path(path, 'rgba(255,40,40, 1.0)');

                    _is_play_animation = true;

                    setTimeout(function () {
                        _is_play_animation = false;

                        // erase path
                        erase_path(path);

                        // draw face
                        draw_face(_board.FaceItems[path.Start.X][path.Start.Y], path.Start.X, path.Start.Y);
                        draw_face(_board.FaceItems[path.End.X][path.End.Y], path.End.X, path.End.Y);
                    }, 300);
                }
            }
        }

        function btnOrder_Click() {
            if (_is_play_animation) return;
            if (_board_state == 2) return;
            if (localStorage == null || _ctx == null) return;

            if (_timer_time != null) {
                _board_state = 1;
                document.getElementById('btnOrder').value = '恢复游戏';

                // pause timer
                clearInterval(_timer_time);
                _timer_time = null;
                _pause_time = (new Date()).getTime();

                // draw order
                draw_order(true);
            }
            else {
                _board_state = 0;
                document.getElementById('btnOrder').value = '暂停查看排名';
                
                // draw board
                draw_board();
                
                // restart timer
                _current_time += (new Date()).getTime() - _pause_time;
                _pause_time = null;
                draw_time(calc_time());
                _timer_time = setInterval(function () {
                    draw_time(calc_time());
                }, 500);
            }
        }

        function btnRemoveOrder_Click() {
            if (localStorage == null) return;

            for (var i = 0; i < 5; i++) {
                var keyName = "name" + _boardsize + "_" + i;
                var keyTime = "time" + _boardsize + "_" + i;
                localStorage.removeItem(keyName);
                localStorage.removeItem(keyTime);
            }
        }

        function OnCanvas_MouseMove(e) {
            if (_is_play_animation) return;
            if (_board_state > 0) return;

            if (!e.offsetX) {
                var offsetP = calc_offset(_canvas);
                e.offsetX = e.pageX - offsetP.X;
                e.offsetY = e.clientY - offsetP.Y;
            }
            var x = e.offsetX - _board_m_offset;
            var y = e.offsetY - _board_n_offset;
            var board_p = point2board(x, y);

            if (_ctx != null) {
                //revert old animation
                if (_first_p != null && _old_p != null && (_first_p.X == _old_p.X && _first_p.Y == _old_p.Y)) {
                    // old == first
                }
                else {
                    // != old
                    if (_old_p != null && (board_p == null || !(_old_p.X == board_p.X && _old_p.Y == board_p.Y))) {
                        draw_face(_board.FaceItems[_old_p.X][_old_p.Y], _old_p.X, _old_p.Y);
                    }
                }

                //draw new animation
                if (board_p != null && _board.FaceItems[board_p.X][board_p.Y] > 0) {
                    // !=selected face
                    if (_first_p == null
                    || (_first_p != null && !(_first_p.X == board_p.X && _first_p.Y == board_p.Y))) {
                        draw_face_animation(board_p.X, board_p.Y, 'rgba(255,128,128, 0.8)');
                        _old_p = board_p;
                    }
                }
            }
        }

        function OnCanvas_Click(e) {
            if (e.button != 0) return; // 1 for left button
            if (_is_play_animation) return;
            if (_board_state > 0) return;

            if (!e.offsetX) {
                var offsetP = calc_offset(_canvas);
                e.offsetX = e.pageX - offsetP.X;
                e.offsetY = e.pageY - offsetP.Y;
            }
            var x = e.offsetX - _board_m_offset;
            var y = e.offsetY - _board_n_offset;
            var board_p = point2board(x, y);
            if (board_p == null) return;
            if (_board.FaceItems[board_p.X][board_p.Y] <= 0) return;

            if (_first_p == null) {
                _first_p = board_p;
                draw_face_animation(board_p.X, board_p.Y, 'rgba(255,40,40, 1.0)');
            }
            else if (_first_p.X == board_p.X && _first_p.Y == board_p.Y) return;
            else {
                var path = new PATH();
                path.Start.X = _first_p.X;
                path.Start.Y = _first_p.Y;
                path.End.X = board_p.X;
                path.End.Y = board_p.Y;
                if (_board.IsFit(path)) {
                    if (_ctx != null) {
                        draw_face_animation(path.End.X, path.End.Y, 'rgba(255,40,40, 1.0)');
                        draw_solve_process(path);
                    }
                }
                else {
                    draw_face(_board.FaceItems[_first_p.X][_first_p.Y], _first_p.X, _first_p.Y);

                    _first_p.X = board_p.X;
                    _first_p.Y = board_p.Y;
                    draw_face_animation(board_p.X, board_p.Y, 'rgba(255,40,40, 1.0)');
                }
            }
        }

        // draw to UI
        function draw_board() {
            _ctx.clearRect(0, 0, _width, _height);
            for (var i = 0; i < _board_m + 2; i++) {
                for (var j = 0; j < _board_n + 2; j++) {
                    draw_face(_board.FaceItems[i][j], i, j);
                }
            }
        }

        function draw_face(i, board_x, board_y) {
            var faceNum = img_face_number(i);
            if (faceNum == null) return;

            var m = faceNum.X;
            var n = faceNum.Y;
            _ctx.drawImage(
                _img_face,
                m * _img_face_src_width, n * _img_face_src_height, _img_face_src_width, _img_face_src_height,
                board_x * _img_face_width, board_y * _img_face_height, _img_face_width, _img_face_height);
        }

        function erase_face(board_x, board_y) {
            _ctx.clearRect(board_x * _img_face_width, board_y * _img_face_height, _img_face_width, _img_face_height);
        }

        function draw_face_animation(board_x, board_y, color) {
            _ctx.save();
            _ctx.lineWidth = 3;
            _ctx.strokeStyle = color;
            roundedRectStroke(_ctx, board_x * _img_face_width, board_y * _img_face_height, _img_face_width, _img_face_height, _img_face_radius);
            _ctx.restore();
        }

        function draw_solve_process(path) {
            // draw path
            draw_path(path, 'rgba(255,40,40, 1.0)');

            _is_play_animation = true;

            setTimeout(function () {
                _is_play_animation = false;

                // draw path
                erase_path(path);
                _board.ErasePair(path);
                _first_p = null;

                // check
                if (_board.IsSuccessFinish()) {
                    // set board state
                    _board_state = 2;
                    
                    // stop timer
                    if (_timer_time != null) {
                        clearInterval(_timer_time);
                        _timer_time = null;
                    }

                    // draw order
                    var tms = calc_time();
                    insert_score(tms);
                    draw_order(false);
                    draw_time(tms);

                    // clear time
                    _current_time = null;
                }
                else {
                    if (!_board.GetHint(path)) {
                        for (var i = 0; i < 1000; i++) {
                            _board.RearrangeBoard();
                            if (_board.GetHint(path)) {
                                draw_board();
                                break;
                            }
                        }
                    }
                }
            }, 100);
        }

        function draw_order(showTitle) {
            var x = (_board_m + 2) * _img_face_width / 2;
            var y = 45;

            _ctx.save();
            _ctx.clearRect(0, 0, _width, _height);
            _ctx.fillStyle = "orange";
            if (showTitle) {
                _ctx.font = "32pt Arial";    
                _ctx.fillText("排名", x - 45, y);
            }
            _ctx.font = "22pt Arial";
            for (var i = 0; i < 5; i++) {
                var keyName = "name" + _boardsize + "_" + i;
                var keyTime = "time" + _boardsize + "_" + i;
                var orderName = localStorage.getItem(keyName);
                var orderTime = localStorage.getItem(keyTime);
                if (orderName != null && orderTime != null) {
                    var itemString = (i + 1).toString() + ". " + format_time_string(orderTime) + "  " + orderName;
                    _ctx.fillText(itemString, x - 130, y + 40 * (i + 1));
                }
            }
            _ctx.restore();
        }

        function draw_time(tms) {
            var x = (_board_m + 2) * _img_face_width / 2 - 83;
            var y = 45;

            _ctx.save();
            _ctx.clearRect(x, 0, 250, _img_face_height);
            _ctx.font = "32pt Arial";
            _ctx.fillStyle = "orange";
            _ctx.fillText(format_time_string(tms), x, y);
            _ctx.restore();
        }

        function draw_path(path, color) {
            _ctx.save();
            
            _ctx.save();
            _ctx.fillStyle = "transparent";
            _ctx.fillRect(path.Start.X * _img_face_width, path.Start.Y * _img_face_height, _img_face_width, _img_face_height);
            _ctx.fillRect(path.End.X * _img_face_width, path.End.Y * _img_face_height, _img_face_width, _img_face_height);
            _ctx.restore();
            
            _ctx.globalCompositeOperation = 'destination-over';
           
            _ctx.lineWidth = 3;
            _ctx.lineCap = 'round';
            _ctx.lineJoin = 'round';
            _ctx.strokeStyle = color;
            _ctx.beginPath();
            _ctx.moveTo(path.Start.X * _img_face_width + _img_face_width / 2, path.Start.Y * _img_face_height + _img_face_height / 2);
            _ctx.lineTo(path.MidOne.X * _img_face_width + _img_face_width / 2, path.MidOne.Y * _img_face_height + _img_face_height / 2);
            _ctx.lineTo(path.MidTwo.X * _img_face_width + _img_face_width / 2, path.MidTwo.Y * _img_face_height + _img_face_height / 2);
            _ctx.lineTo(path.End.X * _img_face_width + _img_face_width / 2, path.End.Y * _img_face_height + _img_face_height / 2);
            _ctx.stroke();

            _ctx.restore();
        }

        function erase_path(path) {
            erase_from_to(path.Start, path.MidOne);
            erase_from_to(path.MidOne, path.MidTwo);
            erase_from_to(path.MidTwo, path.End);
        }

        function erase_from_to(from, to) {
            if (from.X < to.X) {
                _ctx.clearRect(from.X * _img_face_width, from.Y * _img_face_height, _img_face_width * (to.X - from.X + 1), _img_face_height * (to.Y - from.Y + 1));
            }
            else if (from.X > to.X) {
                _ctx.clearRect(to.X * _img_face_width, to.Y * _img_face_height, _img_face_width * (from.X - to.X + 1), _img_face_height * (from.Y - to.Y + 1));
            }
            else {
                if (from.Y < to.Y) {
                    _ctx.clearRect(from.X * _img_face_width, from.Y * _img_face_height, _img_face_width * (to.X - from.X + 1), _img_face_height * (to.Y - from.Y + 1));
                }
                else {
                    _ctx.clearRect(to.X * _img_face_width, to.Y * _img_face_height, _img_face_width * (from.X - to.X + 1), _img_face_height * (from.Y - to.Y + 1));
                }
            }
        }

        function roundedRectStroke(ctx, x, y, width, height, radius) {
            x += 2;
            y += 2;
            width -= 4;
            height -= 4;
            ctx.beginPath();
            roundedRectCore(ctx, x, y, width, height, radius);
            ctx.stroke();
        }

        function roundedRectFill(ctx, x, y, width, height, radius) {
            x += 2;
            y += 2;
            width -= 4;
            height -= 4;
            ctx.beginPath();
            roundedRectCore(ctx, x, y, width, height, radius);
            ctx.fill();
        }

        function roundedRectCore(ctx, x, y, width, height, radius) {
            ctx.moveTo(x, y + radius);
            ctx.lineTo(x, y + height - radius);
            ctx.quadraticCurveTo(x, y + height, x + radius, y + height);
            ctx.lineTo(x + width - radius, y + height);
            ctx.quadraticCurveTo(x + width, y + height, x + width, y + height - radius);
            ctx.lineTo(x + width, y + radius);
            ctx.quadraticCurveTo(x + width, y, x + width - radius, y);
            ctx.lineTo(x + radius, y);
            ctx.quadraticCurveTo(x, y, x, y + radius);
        }

        // utility function
        function point2board(x, y) {
            var gridPoint = new Point();
            gridPoint.X = double2int(x / _img_face_width);
            gridPoint.Y = double2int(y / _img_face_height);

            // check we're not out of bounds
            if (gridPoint.X > _board_m || gridPoint.Y > _board_n
                || gridPoint.X < 1 || gridPoint.Y < 1) {
                gridPoint = null;
            }
            return gridPoint;
        }

        function img_face_number(i) {
            // when bound, return
            if (i <= 0) return null;

            var gridPoint = new Point();
            i = (i - 1) % (_img_face_m * _img_face_n);
            gridPoint.X = i % _img_face_m;
            gridPoint.Y = double2int(i / _img_face_m);

            return gridPoint;
        }

        function calc_offset(element) {
            if(element==null) return null;

            var offsetP = new Point();
            var cur = element;
            offsetP.X = cur.offsetLeft;
            offsetP.Y = cur.offsetTop;

            while (cur.offsetParent != null) {
                cur = cur.offsetParent;
                if (cur.offsetLeft) {
                    offsetP.X += cur.offsetLeft;
                    offsetP.Y += cur.offsetTop;
                }
            }

            return offsetP;
        }

        function calc_time() {
            return (new Date()).getTime() - _current_time;
        }

        // support Number and String
        function double2int(x) {
            //return x > 0 ? Math.floor(x) : Math.ceil(x);
            //return parseInt(x);
            //return ~~x;
            return (0 | x);
        }

        function format_time_string(tms) {
            var ts = double2int(tms / 1000);
            var hh = double2int(ts / 3600);
            var mm = double2int((ts % 3600) / 60);
            var ss = ts % 60;
            return (format_number(hh) + ':' + format_number(mm) + ':' + format_number(ss));
        }

        function format_number(n) {
            return n < 10 ? ('0' + n) : n.toString();
        }

        function insert_score(tms) {
            var i = 0;
            for (i = 0; i < 5; i++) {
                var keyTime = "time" + _boardsize + "_" + i;
                var orderTime = localStorage.getItem(keyTime);
                if (orderTime == null) break;
                else {
                    var tempTime = double2int(orderTime);
                    if (tms < tempTime) break;
                }
            }

            var name = "匿名";
            if (i < 5) {
                name = localStorage.getItem("last_name");
                if (name == null || name == "") {
                    name = "匿名";
                }

                name = prompt("请输入姓名", name);
                if (name == null || name == "") {
                    name = "匿名";
                }
                else if (name.length > 12) {
                    name = name.substr(0, 12);
                }

                localStorage.setItem("last_name", name)
            }

            var oldOrderName = name;
            var oldOrderTime = tms;
            
            for (; i < 5; i++) {
                var keyName = "name" + _boardsize + "_" + i;
                var keyTime = "time" + _boardsize + "_" + i;
                var orderName = localStorage.getItem(keyName);
                var orderTime = localStorage.getItem(keyTime);

                localStorage.setItem(keyName, oldOrderName);
                localStorage.setItem(keyTime, oldOrderTime);

                if (orderName == null || orderTime == null) break;
                oldOrderName = orderName;
                oldOrderTime = orderTime;
            }
        }
    </script>
</head>
<body>
    <table border="1" cellpadding="0" cellspacing="0" style="width: 600px; height: 400px; margin: auto;">
        <tr style="width: 100%;">
            <td style="width: 50%;">
                连连看
            </td>
            <td style="width: 50%;">
                <input id='btnNewGame' name="btnNewGame" type="button" value="新游戏" onclick="btnNewGame_Click()" />
            </td>
        </tr>
        <tr style="width: 100%;">
            <td style="width: 50%;">
                棋盘大小
            </td>
            <td style="width: 50%;">
                <select id="boardsize" style="width: 200px;">
                    <option value="1">小</option>
                    <option value="2" selected="selected">中</option>
                    <option value="3">大</option>
                </select>
            </td>
        </tr>
        <tr style="width: 100%;">
            <td style="width: 50%;">
                操作
            </td>
            <td style="width: 50%;">
                <input id='btnHint' name="btnHint" type="button" value='提示' onclick="btnHint_Click()" />
                &nbsp;&nbsp;
                <input id='btnOrder' name="btnOrder" type="button" value='暂停查看排名' onclick="btnOrder_Click()" />
                &nbsp;&nbsp;
                <input id='btnRemoveOrder' name="btnRemoveOrder" type="button" value='清除排名' onclick="btnRemoveOrder_Click()" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <canvas id="canvas">
                    <span style="background-color:Aqua;">你的浏览器不支持Html5，请用Chrome、Opera、Firefox、Safari或IE9</span>
                </canvas>
            </td>
        </tr>
    </table>
    <table style="width: 300px;"></table>
</body>
</html>
