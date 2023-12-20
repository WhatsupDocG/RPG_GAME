const acceptBtn = CreateButton('Accept changes', 'acceptButton', 'acceptStroke');
acceptBtn.addEventListener('click', () => acceptChanges());
newDiv.appendChild(acceptBtn);

function acceptChanges() {
    
    let name = document.getElementById('input0').value;
    
    updateData({
        id: getUrlID(),
        name: name
    }, 'http://localhost:9104/location/location/');
    
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