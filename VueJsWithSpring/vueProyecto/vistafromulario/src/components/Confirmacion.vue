<template>
  <div>
   <Header />
        <h1>Confirmacion de reservacion:</h1>
        <br><br><br>
        <div id="confirm-reservation">
             Nombre del usuario:
             <label>{{reserva.nombre}} {{reserva.apellido1}} {{reserva.apellido2}}</label>
             <br><br>

             Fecha de nacimiento:
             <label>{{reserva.fecha}}</label>
             <br><br>

             Correo electronico:
             <label>{{reserva.email}}</label>
             <br><br>

             Numero de tarjeta de credito:
             <label>{{reserva.tarjeta}}</label>
             <br><br>

             Fecha de check-in:
             <label>{{fechas.fechaini}}</label>
             <br><br>

             Fecha de check-out:
             <label>{{fechas.fechafin}}</label>
             <br><br>

             <br><br><br><br>
            <button name="button" v-on:click="confirmar"> Reservar </button>
             <br><br><br><br>
         </div>
    <Footer />
  </div>
</template>

<script>

import Header from './Header/Header';
import Footer from './Footer/Footer';

export default {

  name: 'Confirmacion',

  components: {
        Header,
        Footer
  },

  data() {
    return {

        reservaPost : {

            nombre: "",
            apellido: "",
            correo: "",
            fechaIn: "",
            fechaOut: "",
            tarjeta: ""
        },

        fechas: JSON.parse(localStorage.getItem("fechas")),
        hotel: JSON.parse(localStorage.getItem("selectedHotel")),
        habitacion: JSON.parse(localStorage.getItem("selectedRoom")),
        reserva: JSON.parse(localStorage.getItem("reserva")),
    }
  },

  methods: {

      confirmar: function () {

        this.reservaPost.nombre = this.reserva.nombre,
        this.reservaPost.apellido = this.reserva.apellido1,
        this.reservaPost.correo = this.reserva.email,
        this.reservaPost.fechaIn = this.fechas.fechaini,
        this.reservaPost.fechaOut = this.fechas.fechafin,
        this.reservaPost.tarjeta = this.reserva.tarjeta,

        this.$http.post('http://www.json-generator.com/api/json/get/cemCyNSvuG?indent=2', this.reservaPost).then(
              function() {
              alert("Reserva realizada correctamente.")
            },
            function() {
                alert("No se pudo hacer la reserva correctamente.")
        })
        window.location.href=""
      }
  }
}
</script>

<style scoped>
</style>