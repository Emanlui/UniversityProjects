const fs = require('fs');

module.exports = {
    addRoutePage: (req, res) => {
        res.render('add-route.ejs', {
            title: "Bienvenido al taller | Agregar un empleado"
            ,message: ''
        });
    },
    addRoute: (req, res) => {

        let message = '';
        let longStart = req.body.longStart;
        let latStart = req.body.latStart;
        let longFinish = req.body.longFinish;
        let latFinish = req.body.latFinish;
        let number_of_route = req.body.number_of_route;
        let name_of_route = req.body.name_of_route;

        let userQuery = "SELECT * FROM `route` WHERE number_of_route = '" + number_of_route + "'";

        db.query(userQuery, (err, result) => {
            if (err) {
                return res.status(500).send(err);
            }
            if (result.length > 0) {
                message = 'El empleado ya existe';
                res.render('add-route.ejs', {
                    message,
                    title: "Bienvenido al taller | Agregar un empleado"
                });
            } else {
                // check the filetype before uploading it
               
                    // send the employee's details to the database
                    let query = "insert into route (longStart,latStart,longFinish,latFinish,number_of_route, name_of_route) VALUES('" +
                    longStart + "', '" + latStart + "', '" + longFinish + "', '" + latFinish + "', '" + number_of_route + "', '" + name_of_route + "')";
                    db.query(query, (err, result) => {
                        if (err) {
                            return res.status(500).send(err);
                        }
                        res.redirect('/');
                    });
                }
            });
    },
    addLocationPage: (req, res) => {

        let id = req.params.id;
        let queryRoute = "SELECT * FROM `route` WHERE id = '" + id + "' ";
        let queryLocations = "SELECT * FROM `middle_location` WHERE id_route = '" + id + "' ";


        db.query(queryRoute, (err, resultR) => {
            if (err) {
                return res.status(500).send(err);
            }
            db.query(queryLocations, (err, resultL) => {
            if (err) {
                return res.status(500).send(err);
            }
            res.render('add-location.ejs', {
                title: "Add location"
                ,route: resultR[0]
                ,locations: resultL
                ,message: ''
                });
            });
        });
    },
    addLocation: (req, res) => {
        let id_route = req.params.id;
        let long_location = req.body.long_location;
        let lat_location = req.body.lat_location;

        let query = "insert into `middle_location` (long_location, lat_location, id_route) VALUES ('" + long_location + "','" + lat_location + "','" + id_route + "')";
        db.query(query, (err, result) => {
            if (err) {
                return res.status(500).send(err);
            }
            res.redirect('/addLocation/' + id_route);
        });
    },
    deleteRoute: (req, res) => {
        let id = req.params.id;
        let deleteUserQuery = 'DELETE FROM route WHERE id = "' + id + '"';

        let deleteLocationsQuery = 'DELETE FROM middle_location WHERE id_route = "' + id + '"';

        db.query(deleteLocationsQuery, (err, result) => {
            if (err) {
                return res.status(500).send(err);
            }
            db.query(deleteUserQuery, (err, result) => {
            if (err) {
                return res.status(500).send(err);
            }
            res.redirect('/');
            });
        });
    
    },
    showRoute: (req, res) => {
        let id = req.params.id;

        let query1 = "select * from middle_location l where l.id_route = " + id + ";";
        let query2 = "select * from route where id = " + id + ";";

        db.query(query1, (err, result1) => {
            if (err) {
                return res.status(500).send(err);
            }

            db.query(query2, (err, result2) => {
                if (err) {
                    return res.status(500).send(err);
                }

                res.render('show-route.ejs', {
                    title: "Add location"
                    ,locations: result1
                    ,route: result2
                    ,message: ''
                });
            });
            
        });
    }
};

