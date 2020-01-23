// Variables globales
const express = require('express');
const fileUpload = require('express-fileupload');
const bodyParser = require('body-parser');
const mysql = require('mysql');
const path = require('path');
const app = express();
const {getHomePage} = require('./routes/index');
const {addRoutePage, addRoute, deleteRoute, addLocation, addLocationPage, showRoute} = require('./routes/rode');
const port = 5000;

// Crea la conexion de la base de datos
const db = mysql.createConnection ({
    host: 'localhost',
    user: 'root',
    password: 'Johanherr1024@',
    database: 'login'
});

// Se conecta a la base de datos
db.connect((err) => {
    if (err) {
        throw err;
    }
    console.log('Connected to database');
});
global.db = db;

// configuracion del middleware
app.set('port', process.env.port || port); // se configura express para que escuche este puerto
app.set('views', __dirname + '/views'); // setea exprees para que apunte a este directorio para todas sus vistas
app.set('view engine', 'ejs'); // configura el sistema de los templates
app.use(express.static(path.join(__dirname,'public')));
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json()); // parsea los datos de los json 
app.use(express.static(path.join(__dirname, 'public'))); // configura express para que use la carpeta public (que sirve para almacenar imagenes)
app.use(fileUpload()); // configura el sistema para que suba carpetas

// Las rutas de la pagina

app.get('/', getHomePage);
app.get('/add', addRoutePage);
app.get('/addLocation/:id', addLocationPage);
app.get('/delete/:id', deleteRoute);
app.get('/showRoute/:id', showRoute);
app.post('/add', addRoute);
app.post('/addLocation/:id', addLocation);


// Se configura el puerto
app.listen(port, () => {
    console.log(`Server running on port: ${port}`);
});

