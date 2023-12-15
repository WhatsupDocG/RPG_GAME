const div = document.getElementById("control");
saveCurrentPage();

        //Upper zone of window // (Label, search, backBtn)
const mainLabel = CreateLabel('h1', 'Locations', 'mainLabel', "firstStroke");
div.appendChild(mainLabel);

const backBtn = CreateButton('X', 'backButton', 'firstStroke');
backBtn.addEventListener('click', () =>backToMenu());
div.appendChild(backBtn);
function backToMenu() {
   window.location.href = '../../index.html';
}

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
        console.log(searchTerm + " " + item.textContent.toLowerCase());
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


/////////////////////////////////////////////
//Functions of table
function GetValue(param)
{
    return document.getElementById(param).value;
}

function ReturnValue(index)
{
    let mas = ['cell1', 'cell2', 'cell3', 'cell4', 'cell5', 'cell6'];
    return mas[index];
}
  
 function addCharacter() {   
     
    rowArr[amount] = CreateElement('tr');
    rowArr[amount].cell1 = CreateElement('td');
    rowArr[amount].cell1.innerHTML = amount;
    
    rowArr[amount].cell2 = CreateElement('td');
    rowArr[amount].cell2.innerHTML = "Fastage";
    
    rowArr[amount].cell3 = CreateElement('td');
    rowArr[amount].cell3.innerHTML = "Male";
    
    rowArr[amount].cell4 = CreateElement('td');
    rowArr[amount].cell4.innerHTML = 101;
    
    rowArr[amount].cell5 = CreateElement('td');
    rowArr[amount].cell5.innerHTML = 30;
    
    rowArr[amount].cell6 = CreateElement('td');
    rowArr[amount].cell6.innerHTML = 20;
    
    // ПРОВЕРОЧКА  
/*    if (amount >= 2)
    {
        for (var i = 1; i < amount; i++) {
            console.log(typeof(Number(rowArr[i].cell4.innerHTML)) + 
                    Number(rowArr[i].cell4.innerHTML));
            if (Number(rowArr[i].cell2.innerHTML) === "")
            {
            alert("Такое значение уже есть в таблице");
            return;
            }   
        }
    }
 */
    
    // Добавление обработчиков событий для нового элемента
    rowArr[amount].addEventListener("mouseover", function() {
      this.classList.add("over");
    });
    
    rowArr[amount].addEventListener("mouseout", function() {
      this.classList.remove("over");
    });
    
    rowArr[amount].addEventListener("click", function() {
      window.location.href = '../../html/Location/location.html';
    });
  
    //Добавление в таблицу значений(прикрепление?)
    rowArr[amount].appendChild(rowArr[amount].cell1);
    rowArr[amount].appendChild(rowArr[amount].cell2);
    rowArr[amount].appendChild(rowArr[amount].cell3);
    rowArr[amount].appendChild(rowArr[amount].cell4);
    rowArr[amount].appendChild(rowArr[amount].cell5);
    rowArr[amount].appendChild(rowArr[amount].cell6);
    tbody.appendChild(rowArr[amount]);
    

    amount++;
 }
addCharacter();


//Creating addCharacter button
const addCharacterBtn = CreateButton("Add character", "addCharacterBtn", "Buttons");
div.appendChild(addCharacterBtn);

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
    heading_3.innerHTML = "Sex";

    let heading_4 = CreateElement('th');
    heading_4.innerHTML = "Health";

    let heading_5 = CreateElement('th');
    heading_5.innerHTML = "Damage";
    
    let heading_6 = CreateElement('th');
    heading_6.innerHTML = "Level";

    rowArr[0].appendChild(heading_1);
    rowArr[0].appendChild(heading_2);
    rowArr[0].appendChild(heading_3);
    rowArr[0].appendChild(heading_4);
    rowArr[0].appendChild(heading_5);
    rowArr[0].appendChild(heading_6);
}

function CreateElement(param)
{
    return document.createElement(param);
}

function CreateButton(title, id, bClass) {
    let button = document.createElement ('button');
    button.innerText = title;
    button.className = bClass;
    button.id = id;
    document.body.appendChild(button);
    
    return button;
}

function CreateInputText(title, id, bClass) {
    let search = document.createElement ('input');
    search.placeholder = title;
    search.type = "text";
    search.value = "";
    search.className = bClass;
    search.id = id;
    search.autofocus = true;
    document.body.appendChild(search);
    
    return search;
}

function CreateInputBtnOrSubmit(title, type, id, bClass) {
    let search = document.createElement ('input');
    search.placeholder = title;
    search.type = type;
    search.value = "";
    search.title = title;
    search.className = bClass;
    search.id = id;
    search.autofocus = true;
    document.body.appendChild(search);
    
    return search;
}

function CreateLabel(type, title, id, className) {
    let tableLabel = CreateElement(type);
    tableLabel.textContent = title;
    tableLabel.id = id;
    tableLabel.className = className;
    
    return tableLabel;
}