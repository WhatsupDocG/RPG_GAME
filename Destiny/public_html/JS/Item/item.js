        /** First stroke  **/
const div = document.getElementById("control");
const mainLabel = CreateLabel('h1', 'Item', 'mainLabel', "firstStroke");
div.appendChild(mainLabel);

const backBtn = CreateButton('X', 'backButton', 'firstStroke');
backButton.addEventListener('click', () =>backToMenu());
function backToMenu() {
    goToPreviousPage();
}
div.appendChild(backBtn);

        //*   Portraits   *//
const portraitDataDiv = CreateElement('div');
portraitDataDiv.className = "portraitDataDiv";
div.appendChild(portraitDataDiv);

const portrait = CreateImg('../../img/Item/axe.jpg',
    "Avatar's character", "portrait");
portraitDataDiv.appendChild(portrait);

const nameLabel = CreateLabel('h2', 'Name: ' + getUrlName(), '', "portraitData");
portraitDataDiv.appendChild(nameLabel);

const levelLabel = CreateLabel('h2', 'Level: ' + getUrlData("itemLevel"), '', "portraitData");
portraitDataDiv.appendChild(levelLabel);


        //*  Change and Delete Buttons   *//
const buttonDiv = CreateElement('div');
buttonDiv.id = "buttonDiv";
div.appendChild(buttonDiv);

const deleteBtn = CreateButton("Delete item", "deleteBtn", "Buttons");
buttonDiv.appendChild(deleteBtn);
deleteBtn.addEventListener('click', () =>
{
    if (confirmDelete())
        deleteData(getUrlID(), 'http://localhost:9102/item/item/');
});

const heroParams = ['id', 'name', 'armor', 'damage', 'itemLevel'];

const changeBtn = CreateButton("Change item", "changeBtn", "Buttons");
buttonDiv.appendChild(changeBtn);
changeBtn.addEventListener('click', () =>
{
    saveCurrentPage();
    
    let url = '';
    for (let i=0; i < heroParams.length; i++) {
        url = url + heroParams[i] + '=' + getUrlData(heroParams[i]) + '&';
    }
    
    window.location.href = '../../html/Item/editItem.html?' + url;
});