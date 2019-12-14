(function () {
    'use strict';

    /*  https://freebitco.in    */
    const xpath_button_hi = "//button[contains(@id,'bet_hi_button')]";
    const xpath_input = "//input[contains(@class,'value_input')]";
    const xpath_lost_game = "//*[contains(@class,'large-2 small-2 columns center lottery_winner_table_box lottery_winner_table_second_cell')]/font[contains(@color,'re')]";
    const xpath_label = "//*[contains(@class,'show_balance_before_after fa fa-arrows-alt')]/../../*[contains(@class,'large-2 small-2 columns center lottery_winner_table_box lottery_winner_table_first_last_cell')]"
    const xpath_disabled = "//button[contains(@id,'bet_hi_button')][contains(@disabled,'disabled')]";
    const xpath_check_jackpot = "//span[contains(@class,'custom checkbox checked')]/../*[contains(@class,'play_jackpot jackpot_input_margin hidden-field')]";
    /**/
    /*  https://yobit.net/ru/dice   /
    const xpath_input = "//input[contains(@class,'bet')]";
    const xpath_button_lo = "//input[contains(@class,'clDicePlay')][contains(@value,'Roll < 48')]";
    const xpath_button_hi = "//input[contains(@class,'clDicePlay')][contains(@value,'Roll > 52')]";
    const xpath_lost_game = "//a[contains(@class,'nick')]/../../td[contains(@class,'re')]";
    const xpath_label = "//table[contains(@id,'DataTables_Table_0')]/tbody/* /*";
    /**/

    let timeout = 1000;
    let loop = 0;
    let id_loop;
    let rate = 0.00000001;
    let min_rate = 0.00000001;
    let double_rate = 1;
    let double_rate_min = 1;
    let double_rate_max = 19;
    let lose_arr = [];
    let lose = 0;
    let win_arr = [];
    let win = 0;
    let win_los_arr_len = 128;
    let long_que = [0, 0];
    let num_game = 0;
    let sleep = false;
    let coeff = 2.0
    let satoshi =0;
    let win_or_lose = false;
    let sum_satoshi = 0;

    for (let i = 0; i < win_los_arr_len; i++) {
        lose_arr.push(0);
        win_arr.push(0);
    }


    function checkElement(path) {
        let elem = document.evaluate(path, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
        let result = (elem.snapshotLength > 0);
        elem = null;
        return result;
    }

    function clickElements(path, id) {
        let elem = document.evaluate(path, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
        if (!(elem.snapshotLength > 0)) return false;
        if (id > elem.snapshotLength) {
            id = elem.snapshotLength - 1;
        }
        elem.snapshotItem(id).click();
        elem = null;
        return true;
    }

    function getTextContent(path, id) {
        let elem = document.evaluate(path, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
        if (!(elem.snapshotLength > 0)) return false;
        if (id > elem.snapshotLength) {
            id = elem.snapshotLength - 1;
        }
        return elem.snapshotItem(id).textContent;
    }

    function setValue(path, id, value) {
        let elem = document.evaluate(path, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
        if (!(elem.snapshotLength > 0)) return false;
        if (id > elem.snapshotLength) {
            id = elem.snapshotLength - 1;
        }
        elem.snapshotItem(id).value = value;
    }

    function getOldRate(){
        let old_rate = Number(getTextContent(xpath_lost_game, 0));
        satoshi = Math.round(old_rate/min_rate) ;
        sum_satoshi += satoshi;
        //console.log(sum_satoshi);
    }

    function boolWin(){
        if (satoshi>0){
            win_or_lose = true;
            lose_arr[lose]++;
            lose = 0;
            win++;
            if (win > long_que[1]) {
                long_que[1] = win;
            }
        }else{
            win_or_lose = false;
            lose++;
            win_arr[win]++;
            win = 0;
            if (lose > long_que[0]) {
                long_que[0] = lose;
            }
        }

    }

    function getDouble(){
        double_rate = Math.abs(satoshi);
        //double_rate = Math.round(Math.log(Math.abs(satoshi / min_rate)+1) / Math.log(coeff));
        //console.log(double_rate);
    }

    function setDouble(){
        if (!win_or_lose)
            double_rate++;
        else
            double_rate=0;

        if (double_rate <= 0) {
            double_rate = double_rate_min;
        }

        if (sum_satoshi > 0) {
            double_rate = double_rate_min;
            sum_satoshi = 0;
		//	loop = 0;
        }

        if (double_rate >= double_rate_max) {
            double_rate = double_rate_max;
          //  loop = 0;
        }
        
    }

    function setNewRate(){
        rate = min_rate * double_rate;
        //let _kof = (Math.pow(coeff, double_rate)-1)
        //if (_kof<1) _kof =1;
        //rate = min_rate * _kof;
        setValue(xpath_input, 0, toStringRate(rate));
    }

    function toStringRate(rate) {
        if (rate >= 1e-6) {
            return rate.toString(10);
        }
        rate *= 1e+8;
        rate = Math.floor(rate);
        if (rate > 9) {
            return "0.000000" + rate;
        }
        	return "0.0000000" + rate;
    }

    let bool_rate = true;

    function play() {
        getOldRate();
        boolWin();
        getDouble();
        setDouble();
        setNewRate();
        if (loop > 0) {
            clickElements(xpath_button_hi);
        }
    }


    function minPlay() {
        let _rate = rate;
        rate = 2e-8;
        setValue(xpath_input, 0, toStringRate(rate));
        if (flag) {
            clickElements(xpath_button_hi);
        }
        rate = _rate;
        bool_rate = false;
    }

    let bool_game = false;
    let game = true;

    function run() {
        clearTimeout(id_loop);
        if (loop > 0) {
           play();
           loop--;
           id_loop = setTimeout(run, timeout);
        }
    }

    function start(_loop) {
        if (_loop >= 0) {
            loop = _loop
        }
        run();
    }

    function setMinRate(value) {
        if (value >= 0.00000001) {
            min_rate = value;
        }
    }

    function setTimeOut(value) {
        if (value >= 0) {
            timeout = value;
        }
    }

    function setDoubleRateMax(value) {
        if (value > 0) {
            double_rate_max = value;
        }
    }

    window.start = start;
    window.setMinRate = setMinRate;
    window.setTimeOut = setTimeOut;
    window.setDoubleRateMax = setDoubleRateMax;
    window.getLoop = function () {
        return loop;
    };
    window.getLoseArr = function () {
        return lose_arr;
    };
    window.getWinArr = function () {
        return win_arr;
    };
    window.getMaxArr = function () {
        return long_que;
    };

    window.setDoubleRateMin = function (value) {
        if (value > 0 && value < double_rate_max)
            double_rate_min = Math.floor(value);
    };

    window.getTimeOut = function () {
        return timeout;
    };

    while (checkElement(xpath_check_jackpot)){
        clickElements(xpath_check_jackpot, 0);
    }
    start(10000);
})();
