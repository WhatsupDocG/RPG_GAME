const div = document.getElementById("control");


        //Upper zone of window // (Label, search, backBtn)
const mainLabel = CreateLabel('h1', 'Add location', 'mainLabel', "firstStroke");
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
const labels = ['name'];

let label = CreateLabel('label', labels[0], 'label'+0, 'fieldLabels');
newDiv.appendChild(label);

let input = CreateInputText("", "input"+0, 'inputFields');
newDiv.appendChild(input);