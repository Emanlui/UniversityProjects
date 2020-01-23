<template>
  <div>
    <Header />

        <br><br><br>
        <img align="right" alt="imagen vacia" src="../assets/imagenVacia.jpg">
        <br><br>

        Nombre:
        <input type="text" v-model="prueba.nombre" name="nombre">
        <br><br>

        Primer apellido:
        <input type="text" v-model="prueba.apellido1" name="apellido1">
        <br><br>

        Segundo apellido:
        <input type="text" v-model="prueba.apellido2" name="apellido2">
        <br><br>

        Fecha de nacimiento:
        <input type="date" data-date-inline-picker="true" v-model="prueba.fecha" name="fecha-nacimiento">
        <br><br>

        Correo electronico:
        <input type="email" v-model="prueba.email" name="correo">
        <br><br>

        Numero de tarjeta:

        <input type="text" v-model="prueba.tarjeta" name="tarjeta">

        <br><br><br><br>

        <button name="button" v-on:click="reservar"> Reservar </button>

        <br><br><br><br><br><br><br><br><br><br><br>
    <Footer />
  </div>
</template>

<script>

import Header from './Header/Header';
import Footer from './Footer/Footer';

export default {

    name: 'FormularioHotel',

    data() {

        return {

            prueba : {
                nombre: "",
                apellido1: "",
                apellido2: "",
                tarjeta: "",
                fecha: "",
                email: ""
            },
            habitacion: JSON.parse(localStorage.getItem("selectedRoom")),
            hotel: JSON.parse(localStorage.getItem("selectedHotel")),
        }
      },

    methods: {

        reservar: function () {

            localStorage.setItem("reserva", JSON.stringify(this.prueba));
            window.location.href = 'http://localhost:8081/main#/confirmacion';
        }
    },

    components: {
        Header,
        Footer
    }
}

</script>

<style scoped>


</style>
