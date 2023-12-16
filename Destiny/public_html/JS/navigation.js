// navigation.js

// Сохранение текущей страницы при переходе на новую
function saveCurrentPage() {
    const currentPage = window.location.href;
    localStorage.setItem('previousPage', currentPage);
}

// Возвращение к предыдущей странице
function goToPreviousPage() {
    const previousPage = localStorage.getItem('previousPage');
    console.log(previousPage);
    if (previousPage) {
        window.location.href = previousPage;
    } else {
        console.error('Previous page not found');
        // Возможно, у вас есть стандартная страница возврата в случае отсутствия предыдущей страницы
        // window.location.href = 'defaultPage.html';
    }
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

function fetchTableData(url, attributes, innerId, strUrl, table) {
  // URL бэкенда, который предоставляет данные о персонажах
  let chId = getUrlID();
  const backendUrl = url + chId;

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
      //tbodyItems.innerHTML = '';
      //
      // Проверяем наличие свойства content и его тип (массив)
      if (data && Array.isArray(data)) {
        // Используем data.content вместо data
        data.forEach(item => {
            let id = attributes[0];
            addStrData(item, [id, item.name, item.level], innerId, strUrl, table);
        });
      } else {
        console.error('Неверный формат данных');
      }
    },
    error: function (error) {
      // Обработка ошибок при выполнении запроса
      console.error('Ошибка при получении данных:', error);
    }
  });
}

// Функция, отправляющая запрос на бэкенд и обрабатывающая полученные данные
function fetchTableDataContent(url, id, strUrl, table) {
    // URL бэкенда, который предоставляет данные о персонажах
    const backendUrl = url;

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

        // Проверяем наличие свойства content и его тип (массив)
        if (data.content && Array.isArray(data.content)) {
          // Используем data.content вместо data
          data.content.forEach(character => {
            addStrData(character, [id, character.name, character.characterLevel], id, strUrl, table);
          });
        } else {
          console.error('Неверный формат данных');
        }
      },
      error: function (error) {
        // Обработка ошибок при выполнении запроса
        console.error('Ошибка при получении данных:', error);
      }
    });
}

// Функция добавления персонажа в таблицу и привязки обработчиков событий
function addStrDataToTable(character, eventHandlers, id, table) {
    const newRow = CreateElement('tr');

    // Создаем ячейки и заполняем данными о персонаже
    for (let key in character) {
      const newCell = CreateElement('td');
      newCell.innerHTML = character[key];
      newRow.appendChild(newCell);
    }

    // Присваиваем строке идентификатор, чтобы можно было легко найти ее позже
    newRow.setAttribute(id, character.id);

    // Добавление обработчиков событий для нового элемента
    if (eventHandlers && typeof eventHandlers === 'function') {
      eventHandlers(newRow);
    }

    // Добавляем строку в таблицу
    table.appendChild(newRow);
}

// Функция добавления персонажа в таблицу
function addStrData(character, attributes, innerId, strUrl, table) {
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
        let characterId = this.getAttribute(attributes[0]);
        let name = "";
        let level = "";
        if (attributes[1] != "")
            name = "&name=" + attributes[1];
        if (attributes[2] != "")
            level = "&level=" + attributes[2];
        
        window.location.href = strUrl + characterId + name + level;
      });
    };

    // Вызываем функцию добавления персонажа в таблицу с передачей обработчиков событий
    addStrDataToTable(character, eventHandlers, innerId, table);
}

