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