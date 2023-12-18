const div = document.getElementById("control");


        //Upper zone of window // (Label, search, backBtn)
const mainLabel = CreateLabel('h1', 'Characters', 'mainLabel', "firstStroke");
div.appendChild(mainLabel);

const backBtn = CreateButton('X', 'backButton', 'firstStroke');
backBtn.addEventListener('click', () =>backToMenu());
div.appendChild(backBtn);
function backToMenu() {
    goToPreviousPage();
}

const newDiv = document.createElement('div');
div.appendChild(newDiv);

// Текстовые поля
const labels = ['Name', 'Sex', 'Health', 'Damage', 'Level'];

for (let i = 0; i < labels.length; i++) {
    const label = CreateLabel('label', labels[i], 'label${i}', 'fieldLabels');
    const input = CreateInputText("", "input${i}"+labels[i], 'inputFields');
    newDiv.appendChild(label);
    newDiv.appendChild(input);
}

// Кнопка "Принять изменения"
const acceptBtn = CreateButton('Accept changes', 'acceptButton', 'acceptStroke');
acceptBtn.addEventListener('click', () => acceptChanges());
newDiv.appendChild(acceptBtn);

function backToMenu() {
    goToPreviousPage();
}

function acceptChanges() {
    // Обработка изменений, например, чтение значений из текстовых полей
    const name = document.getElementById('input0').value;
    const sex = document.getElementById('input1').value;
    const health = document.getElementById('input2').value;
    const damage = document.getElementById('input3').value;
    const level = document.getElementById('input4').value;

    // Дополнительная логика по обработке изменений
    // ...

    // Вернуться на предыдущую страницу после применения изменений
    goToPreviousPage();
}