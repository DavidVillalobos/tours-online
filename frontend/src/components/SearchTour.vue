<template>
  <div>
    <b-card id="card-search" bg-variant="dark" class="text-center">
      <b-row class="text-light">
        <b-col>
          <h4> 
            Lugar  
            <b-icon-geo-alt-fill variant="danger"></b-icon-geo-alt-fill>
          </h4> 
        </b-col>
        <b-col>
          <h4> 
            <b-icon-calendar-day variant="light"></b-icon-calendar-day>
            Salida  
          </h4> 
        </b-col>
        <b-col>
          <h4> 
            <b-icon-calendar-day variant="light"></b-icon-calendar-day>
            LLegada  
          </h4> 
        </b-col>
        <b-col cols="2"></b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-form-input placeholder="Lugar" v-model="place"></b-form-input>
        </b-col>
        <b-col>
          <b-form-datepicker dark label-help="" placeholder="Fecha de Salida" hide-header v-model="departure" :min="min" class="mb-2" locale="es"></b-form-datepicker>
        </b-col>
        <b-col>
          <b-form-datepicker dark label-help="" placeholder="Fecha de Llegada" hide-header v-model="arrival" :min="min" class="mb-2" locale="es"></b-form-datepicker>
        </b-col>
        <b-col cols="2">
          <b-button block variant="warning" v-on:click="filterTours">
            <b-icon-search variant="dark"></b-icon-search> 
            Buscar
          </b-button>
        </b-col>
      </b-row>
    </b-card>
    <b-card id="result-search" bg-variant="dark">
      <template v-if="tours != 0">
      <b-row id="header-result-search">
        <b-col cols=12>
          <b-card class="tour">
            {{ place }} <br>
            {{ tours.length }} actividades encontradas
          </b-card>
        </b-col>
      </b-row>
      <b-row v-for="(tour, idx) in tours" :key="idx">
        <b-col cols=12>
          <b-card class="tour" v-on:click="viewTour(tour)">
             <b-row>
              <b-col cols=2 class="text-right">
                <b-img rounded v-bind="propsSimpleView" :src="getImageUrl(tour.images[0].id)"  fluid alt="Fluid image">
                </b-img>
                 <b-button class="like-button" pill variant="transparent"><b-icon-heart variant="danger"></b-icon-heart></b-button>
              </b-col>
              <b-col cols=7>
                <strong>{{ tour.city.name}}: {{ tour.name}}</strong> <br>
               <b-icon-clock-history></b-icon-clock-history>
                Duración : {{ tour.duration }} <br>
              </b-col> 
              <b-col cols=2>
                <b-row> 
                  <b-form-rating v-model="tour.rating" variant="warning" readonly show-value no-border></b-form-rating>
                  {{tour.reviews}} opiniones <br>
                  Desde 
                  <h4><br> {{tour.price}}$</h4>
                  Por persona 
                </b-row>
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
            Busqueda de tours por lugar <b-icon-geo-alt-fill variant="danger"></b-icon-geo-alt-fill> <br> 
            Fecha <b-icon-calendar-month variant="dark"></b-icon-calendar-month> de salida y de llegada
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
    const minDate = new Date()
    return {
      propsSimpleView: { width: 220, height: 220 },
      tours: [],
      departure: '',
      arrival: '',
      min: minDate,
      place: ''
    }
  },
  methods: {
    async filterTours(){
        try {
          const response = await fetch(
            'http://localhost:8001/tours/filter' +
            '?place=' + this.place + 
            '&&departure=' + this.departure +
            '&&arrival=' + this.arrival
          , {method: 'GET'});
          this.tours = (await response.json());
        } catch (err) {
          alert("Search failed")
        }
    },
    viewTour(tour){
      alert("Got to tour " + tour.name)
    },
    getImageUrl(imageId) {
      return `http://localhost:8001/only-image?id=${imageId}`;
    }
  }
}

</script>
<style scoped>
#result-search{
  height: 700px;
  margin-top: 5px;
}

#result-search, #card-search{
  border-radius: 0%;
}

.tour{
  margin-top: 5px;
}

</style>
