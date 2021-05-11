<template>
  <div>
    <b-alert
      dismissible
      :variant="alertvariant"
      fade
      :show="showAlert"
      @dismissed="showAlert=false"
      @dismiss-count-down="countDownChanged">
      <h5>
        <b-icon icon="exclamation-circle-fill" variant=alertvariant> </b-icon>
        {{ messageAlert }}
      </h5>
    </b-alert>
    <b-card id="card-search" bg-variant="dark" class="text-center">
      <b-row class="text-light">
        <b-col>
          <h3> <b-icon-geo-alt-fill variant="danger"></b-icon-geo-alt-fill>Lugar </h3> 
        </b-col>
        <b-col>
          <h3> <b-icon-arrow-up-circle-fill variant="success"></b-icon-arrow-up-circle-fill> Salida  </h3> 
        </b-col>
        <b-col>
          <h3> <b-icon-arrow-down-circle-fill variant="danger"></b-icon-arrow-down-circle-fill> LLegada </h3> 
        </b-col>
        <b-col cols="2"></b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-form-input placeholder="Lugar" v-model="place"></b-form-input>
        </b-col>
        <b-col>
          <b-form-datepicker dark label-help="" 
          placeholder=" " hide-header 
          v-model="departure" :min="min"  
          :date-format-options="{ year: 'numeric', month: 'numeric', day: 'numeric' }"
          locale="es"></b-form-datepicker>
        </b-col>
        <b-col>
          <b-form-datepicker dark label-help="" 
          placeholder=" " hide-header 
          v-model="arrival" :min="min" 
          :date-format-options="{ year: 'numeric', month: 'numeric', day: 'numeric' }"
          locale="es">
          </b-form-datepicker>
        </b-col>
        <b-col cols="1">
          <b-button block variant="info" @click="filterTours">
            <b-icon-search variant="light"></b-icon-search> 
          </b-button>
        </b-col>
        <b-col cols="1">
          <b-button block variant="info" @click="cleanTours">
            <b-icon-trash variant="light"></b-icon-trash> 
          </b-button>
        </b-col>
      </b-row>
    </b-card>
    <b-card id="result-search" bg-variant="dark" class="mt-3">
      <b-row v-if="searched" id="header-result-search">
        <b-col cols=12 class="text-left">
            <div class=" text-dark">
              <h4>
                <b-badge variant="warning" v-show="placeTitle !== ''"> {{ placeTitle }} </b-badge>
                <b-badge variant="primary">{{ tours.length }} Actividades encontradas</b-badge> 
              </h4>
            </div>
        </b-col>
      </b-row>
      <template v-if="tours != 0">
      <b-row v-for="(tour, idx) in tours" :key="idx">
        <b-col cols=12>
          <b-card class="mt-3 tour">
             <b-row>
              <b-col cols=3 class="text-right">
                <b-img v-if="tour.images && tour.images[0]" @click="viewTour(tour)" rounded :src="'data:image/jpeg;base64,'+tour.images[0].photo"  fluid alt="Main image Tour"></b-img>
                <template v-if="isLogin">
                  <template v-if="tour.liked">
                    <b-icon-heart-fill @click="removeLike(tour)" class="like-button" variant="danger"></b-icon-heart-fill>
                  </template>
                  <template v-else>
                    <b-icon-heart-fill @click="addLike(tour)" class="like-button" variant="secondary"></b-icon-heart-fill>
                  </template>
                </template>
              </b-col>
              <b-col cols=7 @click="viewTour(tour)">
                <strong>{{ tour.city.name}}: {{ tour.name}}</strong> <br>
               <b-icon-clock-history></b-icon-clock-history>
                Duración : {{ tour.duration }} <br>
              </b-col> 
              <b-col cols=2 @click="viewTour(tour)">
                <b-row v-show="tour.reviews > 0"> 
                  <b-form-rating v-model="tour.rating" size="sm" variant="warning" stars="5" precision="2" show-value-max readonly show-value no-border></b-form-rating>
                </b-row>
                <b-row><b-badge variant="primary">{{tour.reviews}} opiniones </b-badge> </b-row>
                <b-row> Desde </b-row>
                <b-row> <h4><strong>{{tour.price}}$ </strong> </h4> </b-row>
                <b-row> Por persona </b-row>
              </b-col>
             </b-row>
          </b-card>
        </b-col>
      </b-row>
    </template>
    <template v-else>
      <b-row>
        <b-col cols=12>
          <b-card class="text-center">
            <h5>
            GetYourTour  <b-icon-flag-fill variant="warning" rotate="-10" ></b-icon-flag-fill> 
            ofrece tours y actividades en casi todos los países del planeta <br> 
            Todos los tours son guiados por verdaderos expertos y apasionados de la materia <br>
            Busca tours segun el lugar <b-icon-geo-alt-fill variant="danger"></b-icon-geo-alt-fill> 
            o fechas <b-icon-calendar-month variant="dark"></b-icon-calendar-month> 
            </h5>
          </b-card>
        </b-col>
      </b-row>
    </template>
    </b-card>
  </div>
</template>

<script>

export default {
  name: 'SearchTour',
  data() {
    if(this.$session.has('tour')){
      this.$session.remove('tour');
    }
    const minDate = new Date()
    return {
      tours: [],
      departure: '',
      arrival: '',
      min: minDate,
      place: '',
      placeTitle: '',
      departureTitle: '',
      arrivalTitle: '',
      searched: false,
      messageAlert: "",
      showAlert: 0,
      alertvariant: "",
      secShowAlert: 4,
    }
  },
  computed: {
    isLogin(){
      return this.$session.has('user');
    }
  },
  methods: {
    countDownChanged(dismissCountDown) {
        this.showAlert = dismissCountDown
    },
    async filterTours(){
      this.placeTitle = '';
      this.departureTitle = '';
      this.arrivalTitle = '';
      this.tours = [];
      var id_user = 0;
      if(this.isLogin){
        id_user = this.$session.get('user').id;
      }
      try {
        this.$emit("updateOverlay", true);
         const response = await fetch(
          'http://localhost:8080/api/v1/tours/filter' +
          '?place=' + this.place + 
          '&&departure=' + this.departure +
          '&&arrival=' + this.arrival +
          '&&id_user=' + id_user
        , {method: 'GET'});
        this.$emit("updateOverlay", false);
        this.tours = (await response.json());
        this.placeTitle = this.place;
        this.departureTitle = this.departure;
        this.arrivalTitle = this.arrival;
      } catch (err) {
        this.$emit("updateOverlay", false);
        this.messageAlert = "El servidor no responde";
        this.alertvariant = "danger";
        this.showAlert = this.secShowAlert;
      }
      this.searched = true;
    },
    cleanTours(){
      this.tours = [];
      this.place = '';
      this.departure = '';
      this.arrival = '';
      this.searched = false;
    },
    viewTour(tour){
      if(!this.$session.exists()){
        this.$session.start()
      }
      this.$session.set('idTour', tour.id);
      window.location.href = 'http://localhost:8081/tour';
    },
    async addLike(tour){
      if(this.isLogin){
        var Id_User = this.$session.get('user').id;
        try {
          const response = await fetch('http://localhost:8080/api/v1/likes', {
            method: 'POST',
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            id: 0,
            tour: { id : tour.id },
            user: { id : Id_User }
          })
        });
        console.log(await response.json());
        tour.liked = true;
        } catch (err) {
          this.messageAlert = "El servidor no responde";
          this.alertvariant = "danger";
          this.showAlert = this.secShowAlert; 
        }
      }
    },
    async removeLike(tour){
      if(this.isLogin){
        var Id_User = this.$session.get('user').id;
        try {
          const response = await fetch('http://localhost:8080/api/v1/likes'+
          '/tours' + tour.id +
          '/users' + Id_User, {
          method: 'DELETE',
          headers: {
            "Content-Type": "application/json"
          }
        });
        console.log(await response.json());
        tour.liked = false;
        } catch (err) {
          this.messageAlert = "El servidor no responde";
          this.alertvariant = "danger";
          this.showAlert = this.secShowAlert;
        }
      }
    }
  }
}

</script>

<style scoped>

.tour:hover{
  background-color: rgba(255, 255, 255, 0.926)
}

.like-button{
  position:absolute; 
  z-index:1;
  width: 20%;
  height: 20%;
  margin-top: 1%;
  margin-left: -16%;
}

</style>
