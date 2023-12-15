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
