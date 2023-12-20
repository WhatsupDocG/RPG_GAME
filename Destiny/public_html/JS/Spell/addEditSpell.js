const div = document.getElementById("control");


        //Upper zone of window // (Label, search, backBtn)
const mainLabel = CreateLabel('h1', 'Edit spell', 'mainLabel', "firstStroke");
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
const labels = ['name', 'heal', 'damage', 'spellLevel'];

for (let i = 0; i < labels.length; i++) {
    if (labels[i] != 'spellLevel') {
        let label = CreateLabel('label', labels[i], 'label'+i, 'fieldLabels');
        newDiv.appendChild(label);
    }
    
    if (labels[i] != 'spellLevel')  {
        let input = CreateInputText("", "input"+i, 'inputFields');
        newDiv.appendChild(input);
    }
}

newDiv.appendChild(CreateLabel('label', labels[labels.length-1], 'label'+labels.length, 'fieldLabels'));
            
const levels = ['usual']; 
const selectLevel = CreateSelect(levels, 'input4', '');
newDiv.appendChild(selectLevel);