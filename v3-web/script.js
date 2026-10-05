
const monBouton = document.querySelector("button");

const monInput = document.querySelector("input");

const maSection = document.querySelector(".results");


monBouton.addEventListener("click", function() {
    
}); 

monInput.addEventListener("input", async function(){
    if (monInput.value.trim() === "") {
        maSection.innerHTML = "";
        return;
    }

    const resultats = await rechercherMedicaments(monInput.value);
    afficherResultats(resultats);
});


function afficherResultats(resultats) {
    maSection.innerHTML = "";   // on vide les anciens résultats

    if (resultats.length === 0) {
        maSection.innerHTML = "<p>Aucun médicament trouvé.</p>";
        return;
    }

    resultats.forEach(function(med) {
        const carte = document.createElement("div");
        carte.className = "card";

        const classeStatut = med.rembourse ? "status--vert" : "status--corail";   
        const classeTaux = med.rembourse ? "card__taux--vert" : "card__taux--corail";

        carte.innerHTML = `
            <span class="card__status ${classeStatut}"></span>
            <p class="card__nom">${med.nom}</p>
            <p class="card__forme">${med.forme}</p>
            <p class="card__taux ${classeTaux}">${med.taux}%</p>
        `;
        maSection.appendChild(carte);
    });
}


async function rechercherMedicaments(nom) {
    const reponse = await fetch(`http://localhost:8080/recherche?nom=${nom}`);
    const donnees = await reponse.json();
    return donnees;
}
