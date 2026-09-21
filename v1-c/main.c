#include <stdio.h>
#include <string.h>

typedef struct {
    char codeCIS[9]; // Code CIS du médicament
    char nom[200]; // Nom du médicament
} Medicament;

typedef struct {
    char codeCIS[9];
    char tauxRemboursement[20];
} Presentation;

void afficherAvecRemboursement(Medicament med, Presentation tableauPresentations[], int nbPresentations);

int chargerMedicaments(const char *nomFichier, Medicament tableau[], int tailleMax) {
    FILE *fichier = fopen(nomFichier, "r");
    if (fichier == NULL) {
        perror("Erreur lors de l'ouverture du fichier");
        return -1;
    }

    char ligne[1000];
    int count = 0;

    while (count < tailleMax && fgets(ligne,sizeof(ligne), fichier) != NULL){
        char *codeCIS = strtok(ligne, "\t");
        char *nom = strtok(NULL, "\t");
        if (codeCIS != NULL && nom != NULL) {
            strncpy(tableau[count].codeCIS, codeCIS, 9);
            tableau[count].codeCIS[8] = '\0';
            strncpy(tableau[count].nom, nom, 200);
            tableau[count].nom[199] = '\0';
            count++;
        }

    }
    fclose(fichier);
    return count;
   }

void rechercherParNom(Medicament tableau[], int nbMedicaments, const char *recherche, Presentation tableauPresentations[], int nbPresentations){    int trouve = 0; 
    for (int i=0; i< nbMedicaments; i++){
        if (strstr(tableau[i].nom, recherche) != NULL){
            afficherAvecRemboursement(tableau[i], tableauPresentations, nbPresentations);
            trouve = 1;
        }  
    }
    if (trouve == 0){
        printf("Medicament non trouvé.\n");
    }
}

int chargerRemboursements(const char *nomFichier, Presentation tableau[], int tailleMax) {
    FILE *fichier = fopen(nomFichier, "r");
    if (fichier == NULL) {
        perror("Erreur ouverture fichier");
        return -1;
    }

    char ligne[1000];
    int count = 0;

    while (count < tailleMax && fgets(ligne, sizeof(ligne), fichier) != NULL) {
        char *colonnes[12] = {NULL};
        int nbColonnes = 0;
        char *debut = ligne;
        char *tab;

        // Tant qu'on trouve une tabulation, on "coupe" la chaîne à cet endroit
        while (nbColonnes < 11 && (tab = strchr(debut, '\t')) != NULL) {
            *tab = '\0';                  // on remplace \t par \0 : fin du morceau actuel
            colonnes[nbColonnes] = debut; // on note où commence ce morceau
            nbColonnes++;
            debut = tab + 1;              // le prochain morceau commence juste après
        }
        colonnes[nbColonnes] = debut;     // dernier morceau (après la dernière tabulation)
        nbColonnes++;

        if (nbColonnes > 8) {  // sécurité : on a bien au moins 9 colonnes (0 à 8)
            strncpy(tableau[count].codeCIS, colonnes[0], 8);
            tableau[count].codeCIS[8] = '\0';

            strncpy(tableau[count].tauxRemboursement, colonnes[8], 19);
            tableau[count].tauxRemboursement[19] = '\0';
            tableau[count].tauxRemboursement[strcspn(tableau[count].tauxRemboursement, "\n")] = '\0';

            count++;
        }
    }

    fclose(fichier);
    return count;
}

void afficherAvecRemboursement(Medicament med, Presentation tableauPresentations[], int nbPresentations) {
    for (int i = 0; i < nbPresentations; i++) {
        if (strcmp(med.codeCIS, tableauPresentations[i].codeCIS) == 0) {
            if (strlen (tableauPresentations[i].tauxRemboursement) == 0 ){
                printf("Nom : %s | Taux de remboursement : non communiqué\n", med.nom);
            } else {
                printf("Nom : %s | Taux de remboursement : %s\n", med.nom, tableauPresentations[i].tauxRemboursement);
            }
            return; // on s'arrête au premier trouvé

        }
    }
    printf("Nom : %s | Taux de remboursement : inconnu\n", med.nom);
}

int main() {
    Medicament tableau[20000];
    int nb = chargerMedicaments("CIS_bdpm_utf8.txt", tableau, 20000);

     Presentation tableau2[20000];
    int nb2 = chargerRemboursements("CIS_CIP_bdpm.txt", tableau2, 20000);

    if (nb == -1) {
        printf("Erreur lors du chargement.\n");
        return 1;
    }

    if (nb2 == -1) {
        printf("Erreur lors du chargement.\n");
        return 1;
    }

    printf("Nombre de médicaments chargés : %d\n\n", nb);

    // On affiche les 5 premiers pour vérifier visuellement
    printf("--- Aperçu des 5 premiers médicaments ---\n");
    for (int i = 0; i < 5 && i < nb; i++) {
        printf("Code CIS : %s | Nom : %s\n", tableau[i].codeCIS, tableau[i].nom);
    }

    char recherche[200];
    printf("Entrez le nom d'un médicament à rechercher : ");
    fgets(recherche, sizeof(recherche), stdin);
    recherche[strcspn(recherche, "\n")] = '\0';
    rechercherParNom(tableau, nb, recherche, tableau2, nb2);


    return 0;
}