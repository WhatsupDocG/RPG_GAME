const div = document.getElementById("control");


        //Upper zone of window // (Label, search, backBtn)
const mainLabel = CreateLabel('h1', 'Edit character', 'mainLabel', "firstStroke");
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
const labels = ['name', 'health', 'damage', 'sex', 'characterLevel'];

for (let i = 0; i < labels.length; i++) {
    if (labels[i] != 'characterLevel') {
        let label = CreateLabel('label', labels[i], 'label'+i, 'fieldLabels');
        newDiv.appendChild(label);
    }
    
    if (labels[i] != 'sex' && labels[i] != 'characterLevel')  {
        let input = CreateInputText("", "input"+i, 'inputFields');
        newDiv.appendChild(input);
    }
}

const sex = ['MALE', 'FEMALE']; 
const selectSex = CreateSelect(sex, 'input3', '');
newDiv.appendChild(selectSex);

newDiv.appendChild(CreateLabel('label', labels[labels.length-1], 'label'+labels.length, 'fieldLabels'));
            
const levels = ['usual', 'silver']; 
const selectLevel = CreateSelect(levels, 'input4', '');
newDiv.appendChild(selectLevel);

// Кнопка "Принять изменения"
const acceptBtn = CreateButton('Accept changes', 'acceptButton', 'acceptStroke');
acceptBtn.addEventListener('click', () => acceptChanges());
newDiv.appendChild(acceptBtn);

function acceptChanges() {
    
    let name = document.getElementById('input0').value;
    let health = parseFloat(document.getElementById('input1').value).toFixed(1);
    let damage = parseFloat(document.getElementById('input2').value).toFixed(1);
    let sex = document.getElementById('input3').value;
    let level = document.getElementById('input4').value;
    
    //for (let i=0; i < labels.length; i++) {
    //    setUrlData(labels[i], name);
    //}
    
    if (level === levels[0])
        level = 1;
    else if (level === levels[1]) 
        level = 2;
    
    updateData({
        id: getUrlID(),
        name: name,
        sex: sex,
        health: health,
        damage: damage,
        characterLevel: level,
        locationId: 1
    }, 'http://localhost:9101/character/character/');
    
    alert("Успешно!");
    goToPreviousPage();
    goToPreviousPage();
}

function loadData() {
    for (let i = 0; i < labels.length; i++) {
        document.getElementById('input'+i).value = getUrlData(labels[i]);
    }
}
loadData();