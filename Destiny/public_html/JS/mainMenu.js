const chBtn = CreateButton("Characters", "control", "controlButtons");
chBtn.addEventListener('click', () =>Character());
function Character() {
    window.location.href = 'html/Character/characters.html';
}

const itemBtn = CreateButton("Items", "control", "controlButtons");
itemBtn.addEventListener('click', () =>Items());
function Items() {
    window.location.href = 'html/Item/items.html';
}

const locationsBtn = CreateButton("Locations", "control", "controlButtons");
locationsBtn.addEventListener('click', () =>Locations());
function Locations() {
    window.location.href = 'html/Location/locations.html';
}

const enemiesBtn = CreateButton("Enemies", "control", "controlButtons");
enemiesBtn.addEventListener('click', () =>Enemies());
function Enemies() {
    window.location.href = 'html/Enemy/enemies.html';
}

const spellsBtn = CreateButton("Spells", "control", "controlButtons");
spellsBtn.addEventListener('click', () =>Spells());
function Spells() {
    window.location.href = 'html/Spell/spells.html';
}

function CreateButton(title, id, bClass) {
    let button = document.createElement ('button');
    button.innerText = title;
    button.className = bClass;
    button.id = id;
    document.body.appendChild(button);
    
    return button;
}