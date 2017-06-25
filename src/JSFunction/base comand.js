function findElements(arg){
    let max_loop = 100;
    let result = [];
    let elem = document.evaluate(arg, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
    let max_element = (elem.snapshotLength>max_loop)?max_loop:elem.snapshotLength;
    for (let i = 0; i<max_element;i++){
        result.push(elem.snapshotItem(i))
    }
    return result
}

function clickElement(find_name){
    let elem = findElements(find_name);
    if(!elem.length) return false;
    if (elem.length>1) console.log("Wring: clickElement("+find_name+"), find elements>1");
    elem[0].click();
    return true;
}

function clickElementSelect(find_name,i){
    let elem = findElements(find_name);
    if(!elem.length) return false;
    if (elem.length<i) {
        console.log("Wring: clickElement("+find_name+"), find elements<i");
        elem[0].click();
    }else{
        elem[i].click();
    }
    return true;
}

function timePouseReturn(find_name){
    let elem = findElements(find_name);
    if(!elem.length) return -1;
    if (elem.length>1) console.log("Wring: clickElement("+find_name+"), find elements>1");
    let time_text=split(elem[0].innerText,':');
    return Number(time_text[0])*60+Number(time_text[1]);
}

//Map

const openMap = function () {
    return clickElement("//*[contains(@onclick,'map')]");
};
const selectMapGreenTerrain = function () {
    return clickElement("//area[contains(@data-id,'1')]");
};
const selectMapRedTerrain = function () {
    return clickElement("//area[contains(@data-id,'3')]");
};
const selectMapBlueTerrain = function () {
    return clickElement("//area[contains(@data-id,'4')]");
};
const selectMapBlackTerrain = function () {
    return clickElement("//area[contains(@data-id,'21')]");
};
const selectMapGreenZone = function () {
    return clickElement("//area[contains(@data-id,'16')]");
};
const selectMapRedZone = function () {
    return clickElement("//area[contains(@data-id,'13')]");
};
const selectMapBlueZone = function () {
    return clickElement("//area[contains(@data-id,'20')]");
};
const selectMapBlackZone = function () {
    return clickElement("//area[contains(@data-id,'14')]");
};
const selectMapZOO = function () {
    return clickElement("//area[contains(@data-id,'2')]");
};
const selectMapKadaf = function () {
    return clickElement("//area[contains(@data-id,'11')]");
};
const selectMapToser = function () {
    return clickElement("//area[contains(@data-id,'5')]");
};
const selectMapWorkshops = function () {
    return clickElement("//area[contains(@data-id,'12')]");
};
const selectMapHospital = function () {
    return clickElement("//area[contains(@data-id,'15')]");
};
const selectMapMaze = function () {
    return clickElement("//area[contains(@data-id,'19')]");
};
const selectMapArena = function () {
    return clickElement("//area[contains(@data-id,'17')]");
};
const selectMapTournaments = function () {
    return clickElement("//area[contains(@data-id,'18')]");
};
const selectMapHouse = function () {
    return clickElement("//area[contains(@data-id,'22')]");
};
const confirmTheTransition = function () {
    return clickElement("//button[contains(@class,'ok')]");
};

//Attack

const openAttackWindow = function () {
    return clickElement("//a[contains(text(),'Бой')]");
};
const selectAttackMob = function () {
    return clickElement("//*[contains(text(),,'Монстры')]/../..");
};
const selectAttackPlayers = function () {
    return clickElement("//*[contains(text(),,'Игроки')]/../..");
};
let selectAttackToser = function () {
    return clickElement("//*[contains(text(),,'Башня')]/../..");
};
const selectEasyMob = function () {
    return clickElementSelect("//a[contains(text(),'АТАКОВАТЬ')][contains(@style,'none')]", 0);
};
const selectNormalMob = function () {
    return clickElementSelect("//a[contains(text(),'АТАКОВАТЬ')][contains(@style,'none')]", 1);
};
const selectHardMob = function () {
    return clickElementSelect("//a[contains(text(),'АТАКОВАТЬ')][contains(@style,'none')]", 2);
};
const clickAttack = function () {
    return clickElement("//*[contains(text(),'Атака')]/..");
};
const closeResultAtack = function () {
    return clickElement("//*[contains(text(),'закрыть')]/..");
};
const healingInAtack = function () {
    return clickElementSelect("//*[contains(@title,'осстанавл')]", 0);
};
const selectEfectAttack1 = function () {
    return clickElementSelect("//*[contains(@class,'ability')]", 0);
};
const selectEfectAttack2 = function () {
    return clickElementSelect("//*[contains(@class,'ability')]", 1);
};

//inventory

const openInventory = function () {
    return clickElement("//*[contains(@title,'Открыть Инвентарь')]");
};


function findElements(arg){
    let max_loop = 100;
    let result = [];
    let elem = document.evaluate(arg, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
    let max_element = (elem.snapshotLength>max_loop)?max_loop:elem.snapshotLength;
    for (let i = 0; i<max_element;i++){
        result.push(elem.snapshotItem(i))
    }
    return result
}

var timerId0 = setInterval(function () {
    if (findElements("//*[contains(text(),'Атака')]/..").length>0){
        if (findElements("//*[contains(@class,'equippeditem inactive')]/../*").length>0) {
            findElements("//*[contains(@class,'equippeditem inactive')]/../*")[0].click()
        }else {
            findElements("//*[contains(text(),'Атака')]/..")[0].click()
        }
    }
},  250);

var timerId1 = setInterval(function () {
    if (findElements("//*[contains(text(),'закрыть')]/../*").length>0){
        findElements("//*[contains(text(),'закрыть')]/../*")[0].click();
    }
},  200);

var timerId2 = setInterval(function () {
    if (findElements("//a[contains(text(),'Бой')]").length>0){
        findElements("//a[contains(text(),'Бой')]")[0].click();
        if (findElements("//a[contains(text(),'АТАКОВАТЬ')][contains(@style,'none')]").length > 0) {
            findElements("//a[contains(text(),'АТАКОВАТЬ')][contains(@style,'none')]")[2].click();
        }
    }
},  2000);

