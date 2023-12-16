
const div = document.getElementById("control");
saveCurrentPage();

    //Upper zone of window //
const mainLabel = CreateLabel('h1', 'Character', 'mainLabel', "firstStroke");
div.appendChild(mainLabel);

const backBtn = CreateButton('X', 'backButton', 'firstStroke');
backBtn.addEventListener('click', () =>backToMenu());
function backToMenu() {
   window.location.href = 'characters.html';
}
div.appendChild(backBtn);

    //PORTRAIT Data //
const portraitDataDiv = CreateElement('div');
portraitDataDiv.className = "portraitDataDiv";
div.appendChild(portraitDataDiv);

const portrait = CreateImg('../../img/Character/portret.jpg',
    "Avatar's character", "portrait");
portraitDataDiv.appendChild(portrait);

const nameLabel = CreateLabel('h2', 'Name: ' + getUrlName(), '', "portraitData");
portraitDataDiv.appendChild(nameLabel);

const levelLabel = CreateLabel('h2', 'Level: '+getUrlLevel(), '', "portraitData");
portraitDataDiv.appendChild(levelLabel);


    //Creating table //
const tableLabelsDiv = CreateElement('div');
tableLabelsDiv.id = 'tableLabelsDiv';
div.appendChild(tableLabelsDiv);
const tableLabelItems = CreateLabel("h3", "Items:", "labelItems", "tableLabels");
const tableLabelSpells = CreateLabel("h3", "Spells:", "labelSpells", "tableLabels");
tableLabelsDiv.appendChild(tableLabelItems);
tableLabelsDiv.appendChild(tableLabelSpells);


    //Table container
let tableDiv = CreateElement('div');
tableDiv.id = "tableDiv";
div.appendChild(tableDiv); 

//Table
let tableItems = CreateElement("table");
let theadItems = CreateElement("thead");
let tbodyItems = CreateElement("tbody");
tableItems.appendChild(theadItems);
tableItems.appendChild(tbodyItems);
tableItems.id = "tableContainer";   
tableDiv.appendChild(tableItems);
//Объявление Данных для ячеек таблицы
let amountItems = 1;
let currentAmountItems = 0;
let rowArrItems = [];
//
CreateItemTableHead();
theadItems.appendChild(rowArrItems[0]);

//TABLE NUMBER 2 - Spells
let tableSpells = CreateElement("table");
let theadSpells = CreateElement("thead");
let tbodySpells = CreateElement("tbody");
tableSpells.appendChild(theadSpells);
tableSpells.appendChild(tbodySpells);
tableSpells.id = "tableContainer";
tableDiv.appendChild(tableSpells);
//Объявление Данных для ячеек таблицы
let amountSpells = 1;
let currentAmountSpells = 0;
let rowArrSpells = [];
//
CreateSpellTableHead();
theadSpells.appendChild(rowArrSpells[0]);
 
  
          //Attaching data to table//
fetchTableData(
        'http://localhost:9101/character/character/getItems/',
        'data-character-id', 
        'data-item-id', 
        '../../html/Item/item.html?id=',
        tableItems
);

fetchTableData(
        'http://localhost:9101/character/character/getSpells/',
        'data-character-id', 
        'data-spell-id', 
        '../../html/Spell/spell.html?id=',
        tableSpells
);


////////////////////////////////////////////
    //Functions of table
function GetValue(param)
{
    return document.getElementById(param).value;
}

function ReturnValue(param)
{
    let mas = ['cell1', 'cell2', 'cell3', 'cell4'];
    return mas[param];
}
//Creating elements functions
function CreateElement(param)
{
    return document.createElement(param);
}

function CreateItemTableHead() {
    
    rowArrItems[0] = CreateElement('tr');
    let heading_1 = CreateElement('th');
    heading_1.innerHTML ="ID";
    
    let heading_2 = CreateElement('th');
    heading_2.innerHTML = "Name";
    
    let heading_3 = CreateElement('th');
    heading_3.innerHTML = "Damage";
    
    let heading_4 = CreateElement('th');
    heading_4.innerHTML = "Armor";
    
    let heading_5 = CreateElement('th');
    heading_5.innerHTML = "Level";
    
    rowArrItems[0].appendChild(heading_1);
    rowArrItems[0].appendChild(heading_2);
    rowArrItems[0].appendChild(heading_3);
    rowArrItems[0].appendChild(heading_4);
    rowArrItems[0].appendChild(heading_5);
}

function CreateSpellTableHead() {

    rowArrSpells[0] = CreateElement('tr');
    let heading_1 = CreateElement('th');
    heading_1.innerHTML ="ID";

    let heading_2 = CreateElement('th');
    heading_2.innerHTML = "Name";

    let heading_3 = CreateElement('th');
    heading_3.innerHTML = "Damage";

    let heading_4 = CreateElement('th');
    heading_4.innerHTML = "Heal";
    
    let heading_5 = CreateElement('th');
    heading_5.innerHTML = "Level";
    

    rowArrSpells[0].appendChild(heading_1);
    rowArrSpells[0].appendChild(heading_2);
    rowArrSpells[0].appendChild(heading_3);
    rowArrSpells[0].appendChild(heading_4);
    rowArrSpells[0].appendChild(heading_5);
}

function CreateLabel(type, title, id, className) {
    let tableLabel = CreateElement(type);
    tableLabel.textContent = title;
    tableLabel.id = id;
    tableLabel.className = className;
    
    return tableLabel;
}

function CreateImg(src, alt, id) {
    let portrait = CreateElement('img');
    portrait.src = src;
    portrait.alt = alt;
    portrait.id = id;
    
    return portrait;
}

function CreateButton(title, id, bClass) {
    let button = document.createElement ('button');
    button.innerText = title;
    button.className = bClass;
    button.id = id;
    document.body.appendChild(button);
    
    return button;
}