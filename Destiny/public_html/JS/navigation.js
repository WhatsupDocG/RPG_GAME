// navigation.js

function backToMainMenu() {
    window.location.href = '../../index.html';
    localStorage.removeItem('pageHistory');
    pageHistory.length = 0;
}

let pageHistory = JSON.parse(localStorage.getItem('pageHistory')) || [];

function saveCurrentPage() {
    const currentPage = window.location.href;
    pageHistory.push(currentPage);
    localStorage.setItem('pageHistory', JSON.stringify(pageHistory));
}

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

function setUrlData(key, value) {
    // Получаем текущий URL страницы
    let currentUrl = new URL(window.location.href);
    // Получаем параметры из строки запроса
    let searchParams = new URLSearchParams(currentUrl.search);

    // Устанавливаем новое значение параметра или добавляем новый параметр
    searchParams.set(key, value);

    // Обновляем строку запроса в URL
    currentUrl.search = searchParams.toString();

    // Заменяем текущий URL на новый
    window.history.replaceState({}, '', currentUrl.href);
}


function getUrlData(key) {   
      // Получаем текущий URL страницы
      let currentUrl = new URL(window.location.href);
      // Получаем параметры из строки запроса
      let searchParams = new URLSearchParams(currentUrl.search);

      return searchParams.get(key);
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
    const backendUrl = Data.url;
//Data.id === '' ? Data.url : Data.url + getUrlID()
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
                let is = item;
                let eventHandlers = addStrData(Data.urlStroke, is);
                
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

function addStrData(strUrl, item) {
    
    const eventHandlers = function (row) {
      row.addEventListener("mouseover", function() {
        this.classList.add("over");
      });

      row.addEventListener("mouseout", function() {
        this.classList.remove("over");
      });

      row.addEventListener("click", function() { 
            const is = item;
            let url = "";
            //for (let key in is) {
            //    alert(key + " " + is[key]);
            //}
            
            for (let key in is) {
                url = url + key + '=' + is[key] + "&";
            }

            saveCurrentPage();
            window.location.href = strUrl + url;
      });
    };

    return eventHandlers;
}

function addStrDataToTable(character, eventHandlers, innerId, table) {
    const newRow = CreateElement('tr');

    for (let key in character) {
      const newCell = CreateElement('td');
      newCell.innerHTML = character[key];
      newRow.appendChild(newCell);
    }
    
    newRow.setAttribute(innerId, character.id);

    if (eventHandlers && typeof eventHandlers === 'function') {
      eventHandlers(newRow);
    }

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

function CreateSelect(arr, id, className) {
    const createCharacterUrl = document.createElement('select');
    createCharacterUrl.id = id;
    createCharacterUrl.className = className;

    arr.forEach(item => {
        const optionElement = document.createElement('option');
        optionElement.value = item;
        optionElement.text = item.charAt(0) + item.slice(1);
        createCharacterUrl.add(optionElement);
    });
    
    return createCharacterUrl;
}

function confirmDelete() {
    const isConfirmed = confirm('Вы уверены, что хотите удалить персонажа?');

    return isConfirmed;
}

function addData(data, url) {
    // URL для создания нового персонажа
    const createCharacterUrl = url;

    // Опции запроса
    const requestOptions = {
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data)
    };

    $.ajax({
        url: createCharacterUrl,
        type: requestOptions.type,
        contentType: requestOptions.contentType,
        data: requestOptions.data,
        success: function (data) {
            // Обработка данных, полученных от бэкенда (в данном случае, нового персонажа)
            console.log('Новые данные успешно добавлены:', data);
        },
        error: function (error) {
            // Обработка ошибок при выполнении запроса
            console.error('Ошибка при добавлении новых данных:', error);
        }
    });
}

function deleteData(id, url) {
    // URL для удаления персонажа
    const deleteCharacterUrl = url+id;

    // Опции запроса
    const requestOptions = {
        type: 'DELETE',
        contentType: 'application/json'
    };

    $.ajax({
        url: deleteCharacterUrl,
        type: requestOptions.type,
        contentType: requestOptions.contentType,
        success: function (data) {
            // Обработка данных, полученных от бэкенда (в данном случае, ответа об успешном удалении)
            console.log('Данные успешно удалены:', data);
        },
        error: function (error) {
            // Обработка ошибок при выполнении запроса
            console.error('Ошибка при удалении данных:', error);
        }
    });
    goToPreviousPage();
}

function updateData(data, url) {
    const updateCharacterUrl = url + data.id;

    // Опции запроса
    const requestOptions = {
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(data)
    };

    $.ajax({
        url: updateCharacterUrl,
        type: requestOptions.type,
        contentType: requestOptions.contentType,
        data: requestOptions.data,
        success: function (data) {
            console.log('Данные успешно обновлены:', data);
        },
        error: function (error) {
            console.error('Ошибка при обновлении данных:', error);
        }
    });
}