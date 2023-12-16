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

async function getCharacterName(id) {
  const backendUrl = 'http://localhost:9101/character/character/' + id;
  
  try {
    const response = await fetch(backendUrl, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    });

    if (!response.ok) {
      throw new Error('Ошибка при получении данных');
    }

    const data = await response.json();
    return data.name;
  } catch (error) {
    console.error(error);
    return null; // или любое другое значение по умолчанию
  }
}

function fetchTableData(url, id, innerId, strUrl, table) {
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
          addStrData(item, id, innerId, strUrl, table);
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
        tbody.innerHTML = '';

        // Проверяем наличие свойства content и его тип (массив)
        if (data.content && Array.isArray(data.content)) {
          // Используем data.content вместо data
          data.content.forEach(character => {
            addStrData(character, id, id, strUrl, table);
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
function addStrData(character, id, innerId, strUrl, table) {
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
        let characterId = this.getAttribute(id);
        let characterName = this.getAttribute('data-character-name');
        window.location.href = strUrl + characterId + "&name=" +characterName;
      });
    };

    // Вызываем функцию добавления персонажа в таблицу с передачей обработчиков событий
    addStrDataToTable(character, eventHandlers, innerId, table);
}

