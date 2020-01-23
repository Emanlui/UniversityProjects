<template>
  <div>
    <Header />
    <br><br>

    <table width="45%" align="center" border="0">

        <td><label>Nombre:</label>
        <br>
        <input type="text" v-model="nombreHotel.nombre" name="filtro">
        <br><br>
        <button v-on:click="buscarHotel">Buscar</button></td>

        <td><label>Filtros:</label>
        <br>
        <button  style="margin:5px;"><router-link to="hotelesTodos">Listar todos los hoteles</router-link></button>
        <button  style="margin:5px;"><router-link to="hotelesPuntuacion">Listar hoteles por puntuacion</router-link></button>
        <button  style="margin:5px;"><router-link to="hotelesPais">Listar hoteles por pais</router-link></button>
        <button  style="margin:5px;"><router-link to="hotelesRegion">Listar hoteles por region</router-link></button>
        </td>

     </table>

    <br><br>
    <img src="https://i.postimg.cc/RVvK15nJ/hotel-con-encanto-en-granada-1.jpg">
    <br><br><br><br>

    <button><router-link to="reservaciones">Ver Reservaciones</router-link></button>

    <br><br><br>

    <Footer />
  </div>
</template>

<script>
import Header from './Header/Header';
import Footer from './Footer/Footer';

export default {

  components: {
        Header,
        Footer
  },

    data() {

        return {
            hotels : [],
            nombreHotel : {
                nombre: ""
            },
            habitacion: JSON.parse(localStorage.getItem("selectedRoom")),
            hotel: JSON.parse(localStorage.getItem("selectedHotel")),
        }
  },

  methods: {

    buscarHotel : function() {

        localStorage.setItem("nombreHotel", JSON.stringify(this.nombreHotel)),
        this.$http.post('http://www.json-generator.com/api/json/get/cemCyNSvuG?indent=2', this.nombreHotel).then(
                function(response) {
                    this.hotels = response.body,
                    window.location.href="http://localhost:8081/main#/ElegirHotel"
                },
                function() {
                    alert("No encontro ningun hotel con el nombre especificado.")
                })
    }
  },
  created() {

    localStorage.removeItem("nombreHotel");
  }
}

</script>

<style scoped>

</style>