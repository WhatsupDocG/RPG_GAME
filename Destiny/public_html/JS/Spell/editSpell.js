const acceptBtn = CreateButton('Accept changes', 'acceptButton', 'acceptStroke');
acceptBtn.addEventListener('click', () => acceptChanges());
newDiv.appendChild(acceptBtn);

function acceptChanges() {
    
    let name = document.getElementById('input0').value;
    let heal = parseFloat(document.getElementById('input1').value).toFixed(1);
    let damage = parseFloat(document.getElementById('input2').value).toFixed(1);
    let level = document.getElementById('input4').value;
    
    if (level === levels[0])
        level = 1;
    
    updateData({
        id: getUrlID(),
        name: name,
        heal: heal,
        damage: damage,
        spellLevel: level,
        spellType: 1
    }, 'http://localhost:9103/spell/spell/');
    
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