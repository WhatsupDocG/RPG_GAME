// navigation.js

function backToMainMenu() {
    window.location.href = '../../index.html';
    localStorage.removeItem('pageHistory');
    pageHistory.length = 0;
}

let pageHistory = JSON.parse(localStorage.getItem('pageHistory')) || [];

// Сохранение текущей страницы
function saveCurrentPage() {
    const currentPage = window.location.href;
    pageHistory.push(currentPage);
    localStorage.setItem('pageHistory', JSON.stringify(pageHistory));
}

// Возвращение к предыдущей странице
function goToPreviousPage() {

    //localStorage.removeItem('pageHistory');
    //pageHistory.length = 0;

    if (pageHistory.length > 1) {
        let previousPage = pageHistory.pop();
        localStorage.setItem('pageHistory', JSON.stringify(pageHistory));
        window.location.href = previousPage;
    } else if (pageHistory.length === 1) {
        let previousPage = pageHistory.pop();
        window.location.href = previousPage;
        localStorage.removeItem('pageHistory');
        pageHistory.length = 0;
    } else {
        console.error('Previous page not found');
    }
    console.log(pageHistory);
}

function getUrlID() {   
      // Получаем текущий URL страницы
      let currentUrl = new URL(window.location.href);
      // Получаем параметры из строки запроса
      let searchParams = new URLSearchParams(currentUrl.search);

      return searchParams.get('id');
}

function getUrlName() {   
      // Получаем текущий URL страницы
      let currentUrl = new URL(window.location.href);
      // Получаем параметры из строки запроса
      let searchParams = new URLSearchParams(currentUrl.search);

      return searchParams.get('name');
}

function getUrlLevel() {   
      // Получаем текущий URL страницы
      let currentUrl = new URL(window.location.href);
      // Получаем параметры из строки запроса
      let searchParams = new URLSearchParams(currentUrl.search);

      return searchParams.get('level');
}

function fetchTableData(Data) {
    // URL бэкенда, который предоставляет данные о персонажах
    const backendUrl = Data.id === '' ? Data.url : Data.url + getUrlID();

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
        console.log('Полученные данные:', data);

        // Очищаем tbody перед добавлением новых данных
        //tbody.innerHTML = '';

        let resultData = "";
        if (data.content && Array.isArray(data.content)) {
            resultData = data.content;
        } 
        else if (data && Array.isArray(data)) {
            resultData = data;
        }
        else {
          console.error('Неверный формат данных');
        }
        
        if (resultData != "") {
            resultData.forEach(item => {
                let eventHandlers = addStrData([item.id, item.name, 
                    item[Data.levelName]], Data.urlStroke);
                
                addStrDataToTable(item, eventHandlers, Data.innerId, Data.table);
            });
        }
        
      },    
      error: function (error) {
        // Обработка ошибок при выполнении запроса
        console.error('Ошибка при получении данных:', error);
      }
    });
}

// Функция добавления персонажа в таблицу
function addStrData(attributes, strUrl) {
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
        let characterId = attributes[0];
        let name = "";
        let level = "";
        if (attributes[1] != "")
            name = "&name=" + attributes[1];
        if (attributes[2] != "")
            level = "&level=" + attributes[2];
        
        saveCurrentPage();
        window.location.href = strUrl + characterId + name + level;
      });
    };

    return eventHandlers;
}

// Функция добавления персонажа в таблицу и привязки обработчиков событий
function addStrDataToTable(character, eventHandlers, innerId, table) {
    const newRow = CreateElement('tr');

    // Создаем ячейки и заполняем данными о персонаже
    for (let key in character) {
      const newCell = CreateElement('td');
      newCell.innerHTML = character[key];
      newRow.appendChild(newCell);
    }
    
    newRow.setAttribute(innerId, character.id);

    // Добавление обработчиков событий для нового элемента
    if (eventHandlers && typeof eventHandlers === 'function') {
      eventHandlers(newRow);
    }

    // Добавляем строку в таблицу
    table.appendChild(newRow);
}

    //* Functions of creating elements *//
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

function CreateImg(src, alt, id) {
    let portrait = CreateElement('img');
    portrait.src = src;
    portrait.alt = alt;
    portrait.id = id;
    
    return portrait;
}