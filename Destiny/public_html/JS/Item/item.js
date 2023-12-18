const div = document.getElementById("control");
const mainLabel = CreateLabel('h1', 'Item', 'mainLabel', "firstStroke");
div.appendChild(mainLabel);

const backBtn = CreateButton('X', 'backButton', 'firstStroke');
backButton.addEventListener('click', () =>backToMenu());
function backToMenu() {
    goToPreviousPage();
}
div.appendChild(backBtn);

const portraitDataDiv = CreateElement('div');
portraitDataDiv.className = "portraitDataDiv";
div.appendChild(portraitDataDiv);

const portrait = CreateImg('../../img/Item/axe.jpg',
    "Avatar's character", "portrait");
portraitDataDiv.appendChild(portrait);

const nameLabel = CreateLabel('h2', 'Name: ' + getUrlName(), '', "portraitData");
portraitDataDiv.appendChild(nameLabel);

const levelLabel = CreateLabel('h2', 'Level: ' + getUrlLevel(), '', "portraitData");
portraitDataDiv.appendChild(levelLabel);