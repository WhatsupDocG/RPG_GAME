        //**  First Stroke   **//
const div = document.getElementById("control");
const mainLabel = CreateLabel('h1', 'Spell', 'mainLabel', "firstStroke");
div.appendChild(mainLabel);

const backBtn = CreateButton('X', 'backButton', 'firstStroke');
backButton.addEventListener('click', () =>backToMenu());
function backToMenu() {
   goToPreviousPage();
}
div.appendChild(backBtn);


        //**  Portrait   **//
const portraitDataDiv = CreateElement('div');
portraitDataDiv.className = "portraitDataDiv";
div.appendChild(portraitDataDiv);

const portrait = CreateImg('../../img/Spell/fireball.jpg',
    "Avatar's character", "portrait");
portraitDataDiv.appendChild(portrait);

const nameLabel = CreateLabel('h2', 'Name: ' + getUrlName(), '', "portraitData");
portraitDataDiv.appendChild(nameLabel);

const levelLabel = CreateLabel('h2', 'Level: ' + getUrlLevel(), '', "portraitData");
portraitDataDiv.appendChild(levelLabel);


        //*  Change and Delete Buttons   *//
const buttonDiv = CreateElement('div');
buttonDiv.id = "buttonDiv";
div.appendChild(buttonDiv);

const deleteBtn = CreateButton("Delete spell", "deleteBtn", "Buttons");
buttonDiv.appendChild(deleteBtn);
deleteBtn.addEventListener('click', () =>
{
    if (confirmDelete())
        deleteData(getUrlID(), 'http://localhost:9103/spell/spell/');
});

const heroParams = ['id', 'name', 'heal', 'damage', 'spellLevel'];

const changeBtn = CreateButton("Change spell", "changeBtn", "Buttons");
buttonDiv.appendChild(changeBtn);
changeBtn.addEventListener('click', () =>
{
    saveCurrentPage();
    
    let url = '';
    for (let i=0; i < heroParams.length; i++) {
        url = url + heroParams[i] + '=' + getUrlData(heroParams[i]) + '&';
    }
    
    window.location.href = '../../html/Spell/editSpell.html?' + url;
});