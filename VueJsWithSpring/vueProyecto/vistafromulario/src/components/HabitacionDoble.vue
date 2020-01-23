<<<<<<< HEAD
<template>
  <div class="hello">

    <Header />

    <table width="80%" align="center" border="0">
          <tr>
             <td><h2 align="left">Nombre del hotel</h2></td>
             <td><h2 align="center">Región</h2></td>
             <td><h2 align="right">Puntuación</h2></td>
             <td><h2 align="left">Tipo de habitacion</h2></td>
             <td><h2 align="center">Precio</h2></td>
          </tr>
          <tr>
            <td><h2 align="left">{{hotel.name}}</h2></td>
            <td><h2 align="center">{{hotel.region}}</h2></td>
            <td><h2 align="right">{{hotel.score}}</h2></td>
            <td><h2 align="left">{{habitacion[1].tipo_id}}</h2></td>
            <td><h2 align="center">{{habitacion[1].precio}}</h2></td>
            </tr>
    </table>

    <br><br><br>
    <img alt="imagen vacia" src="../assets/imagenVacia.jpg">
    <br><br><br>

    <table width="50%" align="center" border="0">
        <tr>
        <td>Fecha ingreso:<input v-model="fechas.fechaini" type="date" required name="ingreso"></td>
        <td>Fecha salida:<input v-model="fechas.fechafin" type="date" required name="salida"></td>

        <td><button v-on:click="consultar">Consultar fechas</button></td>
        <td><button v-on:click="consultarEIr">Reservar ahora</button></td>
        </tr>
    </table>

    <br><br><br>
    <Footer />
  </div>
</template>

<script>

import Header from './Header/Header';
import Footer from './Footer/Footer';

export default {

  name: 'HabitacionDoble',

  components: {
        Header,
        Footer
  },

  methods: {

        consultar: function () {

            localStorage.setItem("fechas", JSON.stringify(this.fechas));

            alert("Si puede hacer su reservación para esas fechas");

        },

        consultarEIr: function () {

            localStorage.setItem("fechas", JSON.stringify(this.fechas));
            alert("Si puede hacer su reservación para esas fechas");
            window.location.href = 'http://localhost:8081/main#/formularioHotel';
        }
  },

  data() {

          return {
            clicked : false,
            fechas : {
              fechaini: "",
              fechafin: ""

            },
              habitacion: JSON.parse(localStorage.getItem("selectedRoom")),
              hotel: JSON.parse(localStorage.getItem("selectedHotel")),
          }
    },
}
</script>

<style scoped>

</style>
=======
<template>
  <div class="hello">

    <Header />

    <table width="80%" align="center" border="0">
          <tr>
             <td><h2 align="left">Nombre del hotel</h2></td>
             <td><h2 align="center">Región</h2></td>
             <td><h2 align="right">Puntuación</h2></td>
             <td><h2 align="left">Tipo de habitacion</h2></td>
             <td><h2 align="center">Precio</h2></td>
          </tr>
          <tr>
            <td><h2 align="left">{{hotel.name}}</h2></td>
            <td><h2 align="center">{{hotel.region}}</h2></td>
            <td><h2 align="right">{{hotel.score}}</h2></td>
            <td><h2 align="left">{{habitacion[1].tipo_id}}</h2></td>
            <td><h2 align="center">{{habitacion[1].precio}}</h2></td>
            </tr>
    </table>

    <br><br><br>
    <img alt="imagen vacia" src="../assets/imagenVacia.jpg">
    <br><br><br>

    <table width="50%" align="center" border="0">
        <tr>
        <td>Fecha ingreso:<input v-model="fechas.fechaini" type="date" required name="ingreso"></td>
        <td>Fecha salida:<input v-model="fechas.fechafin" type="date" required name="salida"></td>

        <td><button v-on:click="consultar">Consultar fechas</button></td>
        <td><button v-on:click="consultarEIr">Reservar ahora</button></td>
        </tr>
    </table>

    <br><br><br>
    <Footer />
  </div>
</template>

<script>

import Header from './Header/Header';
import Footer from './Footer/Footer';

export default {

  name: 'HabitacionDoble',

  components: {
        Header,
        Footer
  },

  methods: {

        consultar: function () {

            localStorage.setItem("fechas", JSON.stringify(this.fechas));

            alert("Si puede hacer su reservación para esas fechas");

        },

        consultarEIr: function () {

            localStorage.setItem("fechas", JSON.stringify(this.fechas));
            alert("Si puede hacer su reservación para esas fechas");
            window.location.href = 'http://localhost:8081/main#/formularioHotel';
        }
  },

  data() {

          return {
            clicked : false,
            fechas : {
              fechaini: "",
              fechafin: ""

            },
              habitacion: JSON.parse(localStorage.getItem("selectedRoom")),
              hotel: JSON.parse(localStorage.getItem("selectedHotel")),
          }
    },
}
</script>

<style scoped>

</style>
>>>>>>> develop
