plugins { java }

dependencies {
    implementation 'org.apache.poi:poi:4.1.1'
    implementation 'org.apache.poi:poi-ooxml:4.1.1'
    testCompile group: 'junit', name: 'junit', version: '4.12'


    // here starts JavaFX
    implementation 'org.openjfx:javafx:14'

    compile 'org.openjfx:javafx-base:14'
    compile 'org.openjfx:javafx-graphics:14'
    compile 'org.openjfx:javafx-controls:14'
    compile 'org.openjfx:javafx-fxml:14'
    compile 'org.openjfx:javafx-swing:14'
    compile 'org.openjfx:javafx-media:14'
    compile 'org.openjfx:javafx-web:14'
}