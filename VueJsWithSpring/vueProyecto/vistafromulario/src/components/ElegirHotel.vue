
<template>

  <div class="hello">

    <Header />

    <table width="50%" align="center" border="0">
      <tr>
             <td><h2 align="left">Nombre del hotel</h2></td>
             <td><h2 align="center">Región</h2></td>
             <td><h2 align="center">Pais</h2></td>
             <td><h2 align="right">Puntuación</h2></td>
      </tr>
      <tr>
            <td><h2 align="left">{{hotel.name}}</h2></td>
            <td><h2 align="center">{{hotel.region}}</h2></td>
            <td><h2 align="center">{{hotel.country}}</h2></td>
            <td><h2 align="right">{{hotel.score}}</h2></td>
            </tr>
    </table>

    <br><br><br>
    <img alt="imagen vacia" :src=hotel.url>
    <br><br>

    <table width="50%" align="center" border="0">
          <tr>
            <td><button  align="left">Like</button><button  align="left"> Dislike</button></td>
              <td>Habitaciones:<button style="margin:5px;"><router-link to="habitacionFamiliar">Familiar</router-link></button>
            <button style="margin:5px;"><router-link to="habitacionIndividual">Individual</router-link></button>
            <button style="margin:5px;"><router-link to="habitacionDoble">Doble</router-link></button></td>
          </tr>
    </table>

    <div align="left" style="margin-left:200px">
    <td><input type="text" name="titulo" value="Titulo"></td><br>
    <td><textarea rows="4" cols="50"></textarea></td><button>Send</button>
    </div>
    <br><br><br><br>
    <Footer />
  </div>
</template>

<script>
import Header from './Header/Header';
import Footer from './Footer/Footer';


export default {
  name: 'ElegirHotel',
  components: {
        Header,
        Footer
  },

    data(){

        return {
            room : JSON.parse(localStorage.getItem("selectedRoom")),
            hotel : JSON.parse(localStorage.getItem("selectedHotel")),
        }
    },

}
</script>
