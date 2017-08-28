(function () {
    'use strict';

const PATH_INPUT_LOGIN = "//*[contains(@id,'email')]";
const PATH_INPUT_PASSWORD = "//*[contains(@id,'password')]";
const PATH_BTN_SIGN_IN = "//*[contains(@value,'ВОЙТИ')]";
const PATH_BTN_SIGN_OUT = "//*[contains(@value,'ВЫЙТИ')]";
const PATH_OBJ_PRESENT = "//*[contains(@alt,'Коктейль')]/..";
const PATH_BTN_INFO = "//*[contains(@class,'btn-info')]";

let timeout = 250;
let timeout2= 3000;

class User{
    constructor(login,password){
        this.login=login;
        this.password =password;
    }
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

function setValue(path, id,value){
    let elem = document.evaluate(path, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
    if (!(elem.snapshotLength > 0)) return false;
    elem.snapshotItem(id).value=value;
    elem = null;
    return true;
}

function endQuestPresent() {
    clickElements("//*[contains(text(),'Квесты')]", 0);
    setTimeout(function () {
        clickElements("//*[contains(text(),'Делай добро!')]", 0);
        setTimeout(function () {
            clickElements("//*[contains(text(),'Сдать')]", 0);
            setTimeout(function () {
                clickElements(PATH_BTN_INFO,0);
                setTimeout(function () {
                    location.pathname="/start/";
                }, timeout);
            }, timeout);
        }, timeout2);
    }, timeout);
}

function send(users,present) {
    if (users.length > 0) {
        let user = users.pop();
        setTimeout(function () {
            clickElements(present,0);
            setTimeout(function () {
                let path = "//*[contains(@class,'text target_user')]";
                setValue(path,0,user);
                setTimeout(function () {
                    clickElements("//*[contains(@value,'Submit')]",0);
                    setTimeout(send(users), timeout)
                }, timeout);
            }, timeout);
        }, timeout);
    }else{
        setTimeout(endQuestPresent,timeout);
    }
}

function autoIn(user) {
    clickElements(PATH_BTN_SIGN_OUT, 0);
    setTimeout(function () {
        setValue(PATH_INPUT_LOGIN, 0, user.login);
        setValue(PATH_INPUT_PASSWORD, 0, user.password);
        clickElements(PATH_BTN_SIGN_IN, 0);
        setTimeout(function () {
            location.pathname = "/game/";
        }, timeout);
    }, timeout);
}


function openQuestPresent(users,present) {
    clickElements("//*[contains(text(),'Квесты')]", 0);
    setTimeout(function () {
        clickElements("//*[contains(text(),'Делай добро!')]", 0);
        setTimeout(function () {
            clickElements("//*[contains(text(),'Принять')]", 0);
            setTimeout(function () {
                clickElements(PATH_BTN_INFO,0);
                setTimeout(function () {
                    send(users,present);
                }, timeout);
            }, timeout);
        }, timeout2);
    }, timeout);
}

function startSend(user,users,present) {
    if ((location.pathname === "/"))
        location.pathname = "/game/";
    if (location.pathname === "/game/") {
        openQuestPresent(users, present);
    } else {
        autoIn(user);
    }
}

//export
    window.startSend = startSend;
    window.User = User;
    window.PATH_OBJ_PRESENT =PATH_OBJ_PRESENT;
})();

const list_users_1 = ["Nerefeld", "Eyvill Zapor", "Eyvill Pomidor", "gaijinko", "Legster", "LeizZ", "vbn m", "poiuyt", "varCAT", "Liposakcia"];
const list_users_2 = ["Teufel", "Рад12", "Dyma391", "Bes0813", "Наргисса", "Milena88", "Nordrik", "REMPeYDzh", "Никсонс", "ТемныйУчитель"];
const list_users_3 = ["lareik", "Бог из Машины", "_ратник_", "Feremnid", "_leaffox", "weetet", "Tetron", "Зло__", "Nerefeld", "Eyvill Zapor"];


let users = list_users_1;
let user = new User ("login","password");


startSend(user,users,PATH_OBJ_PRESENT);


