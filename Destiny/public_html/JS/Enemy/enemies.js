const div = document.getElementById("control");

        //Upper zone of window // (Label, search, backBtn)
const mainLabel = CreateLabel('h1', 'Enemies', 'mainLabel', "firstStroke");
div.appendChild(mainLabel);

const backBtn = CreateButton('X', 'backButton', 'firstStroke');
backBtn.addEventListener('click', () =>backToMainMenu());
div.appendChild(backBtn);

const searchField = CreateInputText("Search", "searchField", "search");
searchField.addEventListener('input', () =>Searching());
div.appendChild(searchField);

function Searching() {
    const searchTerm = searchField.value.toLowerCase(); // Получаем значение из текстового поля поиска
    const table = document.getElementById('tableContainer'); // Замените 'tableDiv' на фактический id вашей таблицы

    if (!table) {
        console.error('Table not found');
        return;
    }
    
    let rowsInSecondColumn = document.querySelectorAll("#tableContainer tr td:nth-child(2)");
    
    rowsInSecondColumn.forEach((item) => {
        
        let row = item.parentElement;
        if (searchTerm === '') {
            // Если поле поиска пустое, сбрасываем выделение для всех строк
            row.classList.remove('highlighted');
        } else if (item.textContent.toLowerCase().includes(searchTerm)) {
            // Нашли соответствующую строку, выделяем ее
            row.classList.add('highlighted');
        } else {
            // Сбрасываем выделение для остальных строк
            row.classList.remove('highlighted');
        }
        
    });
}


        //Create table //
        
//Table container
let tableDiv = CreateElement('div');
tableDiv.id = "tableDiv";
div.appendChild(tableDiv);

//Table
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
CreateTableHead();
thead.appendChild(rowArr[0]);


        //Attaching data to table
fetchTableData(
    {
    url: 'http://localhost:9105/enemy/enemy',
    urlStroke: '../../html/Enemy/enemy.html?',
    id: '',
    innerId: 'data-enemy-id',
    table: table,
    levelName: 'enemyLevel'
    }
);


//Creating addCharacter button
const addEnemyBtn = CreateButton("Add enemy", "addCharacterBtn", "Buttons");
div.appendChild(addEnemyBtn);
addEnemyBtn.addEventListener('click', () =>addEnemy());
function addEnemy() {
    saveCurrentPage();
    window.location.href = '../../html/Enemy/addEnemy.html';
}

//Creating addCharacter button
const backButton = CreateButton("Back", "backBtn", "Buttons");
div.appendChild(backButton);
backButton.addEventListener('click', () =>backToMenu());
function backToMenu() {
   window.location.href = '../../index.html';
}

//////////////////////////////////
//  Creating's elements functions
function CreateTableHead() {

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