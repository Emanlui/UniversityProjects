import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import Main from './components/Main.vue'
import Reservaciones from './components/Reservaciones.vue'
import Confirmacion from './components/Confirmacion.vue'
import ElegirHotel from './components/ElegirHotel.vue'
import FormularioHotel from './components/FormularioHotel.vue'
import HotelesTodos from './components/HotelesTodos.vue'
import HotelesPuntuacion from './components/HotelesPuntuacion.vue'
import HotelesPais from './components/HotelesPais.vue'
import HotelesRegion from './components/HotelesRegion.vue'
import VueResource from 'vue-resource'
import HabitacionIndividual from './components/HabitacionIndividual.vue'
import HabitacionDoble from './components/HabitacionDoble.vue'
import HabitacionFamiliar from './components/HabitacionFamiliar.vue'


Vue.use(VueResource);
Vue.use(VueRouter);

export const bus = new Vue();

const routes = [
    { path: '/', component: Main},
    { path: '/reservaciones', component: Reservaciones},
    { path: '/confirmacion', component: Confirmacion},
    { path: '/elegirHotel', component: ElegirHotel},
    { path: '/formularioHotel', component: FormularioHotel},
    { path: '/hotelesTodos', component: HotelesTodos},
    { path: '/hotelesPuntuacion', component: HotelesPuntuacion},
    { path: '/hotelesPais', component: HotelesPais},
    { path: '/hotelesRegion', component: HotelesRegion},
    { path: '/habitacionIndividual', component: HabitacionIndividual},
    { path: '/habitacionDoble', component: HabitacionDoble},
    { path: '/habitacionFamiliar', component: HabitacionFamiliar}
];

const router = new VueRouter({
    routes
})

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
