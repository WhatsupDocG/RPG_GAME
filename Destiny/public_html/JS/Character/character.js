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

const nameLabel = CreateLabel('h2', 'Name: Fastage', '', "portraitData");
portraitDataDiv.appendChild(nameLabel);

const levelLabel = CreateLabel('h2', 'Level: 22', '', "portraitData");
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
  
function addItem() {   
     
    rowArrItems[amountItems] = CreateElement('tr');
    rowArrItems[amountItems].cell1 = CreateElement('td');
    rowArrItems[amountItems].cell1.innerHTML = amountItems;
    
    rowArrItems[amountItems].cell2 = CreateElement('td');
    rowArrItems[amountItems].cell2.innerHTML = "Sword";
    
    rowArrItems[amountItems].cell3 = CreateElement('td');
    rowArrItems[amountItems].cell3.innerHTML = "5";
    
    rowArrItems[amountItems].cell4 = CreateElement('td');
    rowArrItems[amountItems].cell4.innerHTML = "10";
    
    rowArrItems[amountItems].cell5 = CreateElement('td');
    rowArrItems[amountItems].cell5.innerHTML = "0";
    
    
    // Добавление обработчиков событий для нового элемента
    rowArrItems[amountItems].addEventListener("mouseover", function() {
      this.classList.add("over");
    });
    
    rowArrItems[amountItems].addEventListener("mouseout", function() {
      this.classList.remove("over");
    });
    
    rowArrItems[amountItems].addEventListener("click", function() {
      window.location.href = '../../html/Item/item.html';
    });
  
    //Добавление в таблицу значений(прикрепление?)
    rowArrItems[amountItems].appendChild(rowArrItems[amountItems].cell1);
    rowArrItems[amountItems].appendChild(rowArrItems[amountItems].cell2);
    rowArrItems[amountItems].appendChild(rowArrItems[amountItems].cell3);
    rowArrItems[amountItems].appendChild(rowArrItems[amountItems].cell4);
    rowArrItems[amountItems].appendChild(rowArrItems[amountItems].cell5);
    tbodyItems.appendChild(rowArrItems[amountItems]);
    

    amountItems++;
 }
 
  
// Функция, отправляющая запрос на бэкенд и обрабатывающая полученные данные
function fetchCharacters() {
  // URL бэкенда, который предоставляет данные о персонажах
  let chId = getUrlID();
  console.log(chId);
  const backendUrl = 'http://localhost:9101/character/character/getItems/' + chId;

  // Опции запроса
  const requestOptions = {
    type: 'GET',
    contentType: 'application/json'
  };

  // Отправляем GET-запрос на бэкенд
  $.ajax({
    url: backendUrl,
    type: requestOptions.type,
    contentType: requestOptions.contentType,
    success: function (data) {
      // Обработка данных, полученных от бэкенда
      console.log('Полученные данные о предметах персонажа:', data);

      // Очищаем tbody перед добавлением новых данных
      tbodyItems.innerHTML = '';

      console.log(data);
      // Проверяем наличие свойства content и его тип (массив)
      if (data && Array.isArray(data)) {
        // Используем data.content вместо data
        data.forEach(item => {
          addItem(item);
        });
      } else {
        console.error('Неверный формат данных о предметах');
      }
    },
    error: function (error) {
      // Обработка ошибок при выполнении запроса
      console.error('Ошибка при получении данных:', error);
    }
  });
}

// Функция добавления персонажа в таблицу и привязки обработчиков событий
function addCharacterToTable(item, eventHandlers) {
  const newRow = CreateElement('tr');

  // Создаем ячейки и заполняем данными о персонаже
  for (let key in item) {
    const newCell = CreateElement('td');
    newCell.innerHTML = item[key];
    newRow.appendChild(newCell);
  }

  // Присваиваем строке идентификатор, чтобы можно было легко найти ее позже
  newRow.setAttribute('data-item-id', item.id);

  // Добавление обработчиков событий для нового элемента
  if (eventHandlers && typeof eventHandlers === 'function') {
    eventHandlers(newRow);
  }

  // Добавляем строку в таблицу
  tableItems.appendChild(newRow);
}

// Функция добавления персонажа в таблицу
function addItem(item) {
  // Функция обработчиков событий для нового элемента
  const eventHandlers = function (row) {
    row.addEventListener("mouseover", function() {
      this.classList.add("over");
    });

    row.addEventListener("mouseout", function() {
      this.classList.remove("over");
    });

    row.addEventListener("click", function() {
      // Получаем id персонажа из атрибута данных
      const characterId = this.getAttribute('data-character-id');
      window.location.href = '../../html/Character/character.html?id=' + characterId;
    });
  };

  // Вызываем функцию добавления персонажа в таблицу с передачей обработчиков событий
  addCharacterToTable(item, eventHandlers);
}

// Вызываем функцию для получения данных о персонажах
fetchCharacters();


////////////////////////////////////////////
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

function addSpell() {   
     
    rowArrSpells[amountSpells] = CreateElement('tr');
    rowArrSpells[amountSpells].cell1 = CreateElement('td');
    rowArrSpells[amountSpells].cell1.innerHTML = amountSpells;
    
    rowArrSpells[amountSpells].cell2 = CreateElement('td');
    rowArrSpells[amountSpells].cell2.innerHTML = "Fireball";
    
    rowArrSpells[amountSpells].cell3 = CreateElement('td');
    rowArrSpells[amountSpells].cell3.innerHTML = "5";
    
    rowArrSpells[amountSpells].cell4 = CreateElement('td');
    rowArrSpells[amountSpells].cell4.innerHTML = "34";
    
    
    // Добавление обработчиков событий для нового элемента
    rowArrSpells[amountSpells].addEventListener("mouseover", function() {
      this.classList.add("over");
    });
    
    rowArrSpells[amountSpells].addEventListener("mouseout", function() {
      this.classList.remove("over");
    });
    
    rowArrSpells[amountSpells].addEventListener("click", function() {
      window.location.href = '../../html/Spell/spell.html';
    });
  
    //Добавление в таблицу значений(прикрепление?)
    rowArrSpells[amountSpells].appendChild(rowArrSpells[amountSpells].cell1);
    rowArrSpells[amountSpells].appendChild(rowArrSpells[amountSpells].cell2);
    rowArrSpells[amountSpells].appendChild(rowArrSpells[amountSpells].cell3);
    rowArrSpells[amountSpells].appendChild(rowArrSpells[amountSpells].cell4);
    tbodySpells.appendChild(rowArrSpells[amountSpells]);
    

    amountSpells++;
 }
addSpell();
addSpell();
addSpell();
addSpell();
addSpell();
addSpell();
addSpell();
addSpell();
addSpell();
addSpell();


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
    heading_3.innerHTML = "Level";

    let heading_4 = CreateElement('th');
    heading_4.innerHTML = "Damage";
    

    rowArrSpells[0].appendChild(heading_1);
    rowArrSpells[0].appendChild(heading_2);
    rowArrSpells[0].appendChild(heading_3);
    rowArrSpells[0].appendChild(heading_4);
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