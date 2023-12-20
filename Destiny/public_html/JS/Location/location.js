const div = document.getElementById("control");


    //Upper zone of window //
const mainLabel = CreateLabel('h1', 'Location', 'mainLabel', "firstStroke");
div.appendChild(mainLabel);

const backBtn = CreateButton('X', 'backButton', 'firstStroke');
backBtn.addEventListener('click', () =>backToMenu());

function backToMenu() {
    goToPreviousPage();
}
div.appendChild(backBtn);


    //PORTRAIT Data //
const portraitDataDiv = CreateElement('div');
portraitDataDiv.className = "portraitDataDiv";
div.appendChild(portraitDataDiv);

const portrait = CreateImg('../../img/Location/dessert.jpg',
    "Avatar's character", "portrait");
portraitDataDiv.appendChild(portrait);

const nameLabel = CreateLabel('h2', 'Name: ' + getUrlName(), '', "portraitData");
portraitDataDiv.appendChild(nameLabel);



        //*  Change and Delete Buttons   *//
const buttonDiv = CreateElement('div');
buttonDiv.id = "buttonDiv";
div.appendChild(buttonDiv);

const deleteBtn = CreateButton("Delete location", "deleteBtn", "Buttons");
buttonDiv.appendChild(deleteBtn);
deleteBtn.addEventListener('click', () =>
{
    if (confirmDelete())
        deleteData(getUrlID(), 'http://localhost:9104/location/location/');
});

const heroParams = ['id', 'name'];

const changeBtn = CreateButton("Change location", "changeBtn", "Buttons");
buttonDiv.appendChild(changeBtn);
changeBtn.addEventListener('click', () =>
{
    saveCurrentPage();
    
    let url = '';
    for (let i=0; i < heroParams.length; i++) {
        url = url + heroParams[i] + '=' + getUrlData(heroParams[i]) + '&';
    }
    
    window.location.href = '../../html/Location/editLocation.html?' + url;
});


    //Creating table //
const tableLabelsDiv = CreateElement('div');
tableLabelsDiv.id = 'tableLabelsDiv';
div.appendChild(tableLabelsDiv);

//Label
const tableLabelEnemies = CreateLabel("h3", "Enemies:", "labelEnemies", "tableLabels");
tableLabelsDiv.appendChild(tableLabelEnemies);


    //Table container
let tableDiv = CreateElement('div');
tableDiv.id = "tableDiv";
div.appendChild(tableDiv); 


let table = CreateElement("table");
let thead = CreateElement("thead");
let tbody = CreateElement("tbody");
table.appendChild(thead);
table.appendChild(tbody);
table.id = "tableContainer";   
tableDiv.appendChild(table);
//Объявление Данных для ячеек таблицы
let amount = 1;
let currentAmount = 0;
let rowArr = [];
//
CreateItemTableHead();
thead.appendChild(rowArr[0]);

 
function CreateItemTableHead() {
    
    rowArr[0] = CreateElement('tr');
    let heading_1 = CreateElement('th');
    heading_1.innerHTML ="ID";
    
    let heading_2 = CreateElement('th');
    heading_2.innerHTML = "Name";
    
    let heading_3 = CreateElement('th');
    heading_3.innerHTML = "Health";
    
    let heading_4 = CreateElement('th');
    heading_4.innerHTML = "Damage";
    
    let heading_5 = CreateElement('th');
    heading_5.innerHTML = "Level";
    
    rowArr[0].appendChild(heading_1);
    rowArr[0].appendChild(heading_2);
    rowArr[0].appendChild(heading_3);
    rowArr[0].appendChild(heading_4);
    rowArr[0].appendChild(heading_5);
}

fetchTableData(
    {
    url: 'http://localhost:9104/location/location/getEnemies/',
    urlStroke: '../../html/Enemy/enemy.html?id=',
    id: true, 
    innerId: 'data-enemy-id',
    table: table,
    levelName: 'enemyLevel'
    }
);