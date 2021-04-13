<template>
  <div>
    <b-card id="card-admin" bg-variant="dark">
      <b-card bg-variant="light">
        <h2 class="text-center">
          <b-icon icon="map-fill"></b-icon>
          Registro de Tour
        </h2>
        <b-row>
          <b-col>
            <b-row>
              <b-col>
              Pais
              <b-form-select v-model="countrySelected" :options="countries" @change="loadCities"></b-form-select>
              </b-col>
              <b-col class="mt-4">
                <b-button block variant="secondary">
                  Agregar país
                </b-button>
              </b-col>
            </b-row>
            <b-row>
            <b-col>
              Ciudad
              <b-form-select v-model="citySelected" :options="cities" @change="includeCity"></b-form-select>
            </b-col>
            <b-col class="mt-4">
              <b-button block variant="secondary">
               Agregar ciudad
              </b-button>
            </b-col>
            </b-row>
            Nombre
            <b-form-input v-model="tour.name"></b-form-input>
            Categoria
            <b-form-input v-model="tour.category"></b-form-input>
          </b-col>
          <b-col>
            Descripcion
            <b-form-textarea no-resize rows=4 v-model="tour.description"></b-form-textarea>
            <b-row>
              <b-col>
                Fecha
                <b-form-datepicker dark label-help="" 
                placeholder=" " hide-header 
                v-model="tour.date" :min="min"  
                :date-format-options="{ year: 'numeric', month: 'numeric', day: 'numeric' }"
                locale="es"></b-form-datepicker>
              </b-col>
              <b-col>
                Duración
                <b-form-timepicker placeholder=" " label-close-button="Cerrar" v-model="tour.duration" hide-header></b-form-timepicker>
              </b-col>
            </b-row>
            <b-row>
              <b-col>
                Cupo
                <b-form-spinbutton v-model="tour.quota" min:1 value=1 ></b-form-spinbutton>
              </b-col>
              <b-col>
                Precio en Dolares
                <b-form-input v-model="tour.price" type="number"></b-form-input>
              </b-col>
            </b-row>
          </b-col>
          <b-col>
            <b-row>
                Incluye
               <b-form-input v-model="tour.includes"></b-form-input>
                No incluye
               <b-form-input v-model="tour.notIncludes"></b-form-input>
                Imagenes
                <b-form-file v-model="imagesForm" multiple accept="image/*" placeholder='Seleccione multiples imagenes' browse-text='' @change="encodeImageFileAsURL"></b-form-file>
            </b-row>
            <b-row class="mt-4">
              <b-col>
                Imagen Principal
              </b-col>
              <b-col>
                 <b-form-select v-model="mainImageSelected" :options="imageNames"></b-form-select>
              </b-col>
            </b-row>
          </b-col>
        </b-row>
        <b-row class="mt-3 text-center">
          <b-col>
            <b-button variant="secondary" @click="resetForm">
              Limpiar
            </b-button>
          </b-col>
          <b-col>
            <b-button variant="primary" @click="createNewTour">
              Registrar
            </b-button>
          </b-col>
          <b-col>
            <b-button variant="danger" @click="images = []">
              <b-icon-trash variant="light"></b-icon-trash>
              Eliminar Imagenes 
            </b-button>
          </b-col>
        </b-row>
      </b-card>
    </b-card>
  </div>
</template>

<script>

export default {
  name: 'CreateTour',
  data() {
    if(!this.$session.has('user') || !this.$session.get('user').admin){
      window.location.href = 'http://localhost:8002';
    }
    const minDate = new Date()
    return {
      min: minDate,
      countries: [],
      cities: [],
      countrySelected: null,
      citySelected: null,
      mainImageSelected: -1,
      imagesForm: [],
      tour :{
        city: null,
        name: '',
        category: '',
        description: '',
        date: '',
        quota: 0,
        duration: '',
        price: 0,
        includes: '',
        notIncludes: '',
        images: []
      }
    }
  },
  async created(){
    const response = await fetch('http://localhost:8001/countries?complete=true', {method: 'GET'});
    let countriesFetch = (await response.json());
    countriesFetch.forEach(country => {
      this.countries.push({text:country.name, value:country})
    });
  },
  computed: {
    imageNames(){
      let names = [{text:'Seleccione una imagen', value:-1}]
      for(let i in this.imagesForm){
        names.push({text:this.imagesForm[i].name, value:i})
      }
      return names;
    }
  },
  methods: {
    resetForm(){
      this.countrySelected = null;
      this.citySelected = null;
      this.mainImageSelected = -1;
      this.cities = [];
      this.tour.name = '';
      this.tour.category = '';
      this.tour.date = '';
      this.tour.description = '';
      this.tour.duration = '';
      this.tour.quota = 0;
      this.tour.price = 0;
      this.tour.includes = '';
      this.tour.notIncludes = '';
      this.imagesForm = [];
      this.images = [];
    },
    loadCities(){
      this.cities = [];
      this.countrySelected.cities.forEach(city => {
        this.cities.push({text:city.name, value:city})
      });
    },
    includeCity(){
      this.tour.city = {name: this.citySelected.name, id: this.citySelected.id}
    },
    encodeImageFileAsURL(){
      console.log('NOT WORKING');
    },
    async createNewTour(){
      alert(JSON.stringify(this.tour))
      try {
        const response = await fetch('http://localhost:8001/tour', {
          method: 'POST',
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(this.tour)
        });
        console.log(response);
        this.messageAlert = "Tour agregado con exito";
        this.alertvariant = "success";
      } catch (err) {
        this.messageAlert = "El servidor no responde";
        this.alertvariant = "danger";
      }
      this.showAlert = this.secShowAlert;
    }
  }
}

</script>
<style scoped>
.alert-content{
  position:absolute; z-index:1;
}
</style>
