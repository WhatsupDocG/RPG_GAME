        //*   First Stroke   *//
const div = document.getElementById("control");
const mainLabel = CreateLabel('h1', 'Enemy', 'mainLabel', "firstStroke");
div.appendChild(mainLabel);

const backBtn = CreateButton('X', 'backButton', 'firstStroke');
backButton.addEventListener('click', () =>backToMenu());
function backToMenu() {
    goToPreviousPage();
}
div.appendChild(backBtn);


        //*   Portrats Data   *//
const portraitDataDiv = CreateElement('div');
portraitDataDiv.className = "portraitDataDiv";
div.appendChild(portraitDataDiv);

const portrait = CreateImg('../../img/Enemy/spider.jpg',
    "Avatar's character", "portrait");
portraitDataDiv.appendChild(portrait);

const nameLabel = CreateLabel('h2', 'Name: ' + getUrlName(), '', "portraitData");
portraitDataDiv.appendChild(nameLabel);

const levelLabel = CreateLabel('h2', 'Level: ' + getUrlData('enemyLevel'), '', "portraitData");
portraitDataDiv.appendChild(levelLabel);


        //*  Change and Delete Buttons   *//
const buttonDiv = CreateElement('div');
buttonDiv.id = "buttonDiv";
div.appendChild(buttonDiv);

const deleteBtn = CreateButton("Delete enemy", "deleteBtn", "Buttons");
buttonDiv.appendChild(deleteBtn);
deleteBtn.addEventListener('click', () =>
{
    if (confirmDelete())
        deleteData(getUrlID(), 'http://localhost:9105/enemy/enemy/');
});

const heroParams = ['id', 'name', 'health', 'damage', 'enemyLevel'];

const changeBtn = CreateButton("Change enemy", "changeBtn", "Buttons");
buttonDiv.appendChild(changeBtn);
changeBtn.addEventListener('click', () =>
{
    saveCurrentPage();
    
    let url = '';
    for (let i=0; i < heroParams.length; i++) {
        url = url + heroParams[i] + '=' + getUrlData(heroParams[i]) + '&';
    }
    
    window.location.href = '../../html/Enemy/editEnemy.html?' + url;
});