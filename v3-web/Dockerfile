FROM eclipse-temurin:17
WORKDIR /app
COPY . .
RUN javac Serveur.java Medicament.java MedicamentRepository.java Presentation.java PresentationRepository.java Repository.java
CMD ["java", "Serveur"]
