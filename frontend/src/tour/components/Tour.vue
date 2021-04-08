<template>
  <div>
    <b-card class="opacity" bg-variant="dark">
      <b-card class="opacity" bg-variant="light">
        <b-row class="justify-content-md-center"> 
          <b-col cols=10>
            <h2>              
                {{tour.city.name}}: {{tour.name}}
            </h2>
          </b-col>
          <b-col cols=2>     
            <b-button variant="warning" @click="returnIndex">
              <b-icon-arrow90deg-left></b-icon-arrow90deg-left> 
                Volver
            </b-button>
          </b-col>
        </b-row> 
        <b-row class="justify-content-md-begin"> 
          <b-col cols=1>
          </b-col>
          <b-col cols=3>
            <b-form-rating v-model="tour.rating" variant="warning" stars="5" precision="1" show-value-max readonly show-value no-border></b-form-rating>
          </b-col>
          <b-col cols=4>
            <b-badge variant="primary">{{ tour.reviews }} Opiniones </b-badge> 
          </b-col>
          <b-col cols=3>
            <template v-if="this.$session.exists() && this.$session.get('user')">
              <template v-if="tour.liked">
                <b-button block variant="light" @click="removeLike(tour)">
                  <b-icon-heart-fill class="like-button" variant="danger"></b-icon-heart-fill> 
                  Eliminar de favoritos
                </b-button>
              </template>
              <template v-else>
                <b-button block variant="light" @click="addLike(tour)">
                  <b-icon-heart-fill class="like-button" variant="secondary"></b-icon-heart-fill> 
                  Añadir a favoritos
                </b-button>
              </template>
            </template>
          </b-col>
        </b-row>
        <b-row class="justify-content-md-center">
          <b-carousel id="carousel"
              :interval="3000"
              controls
              indicators
              img-width="1800"
              img-height="700"
              label-goto-slide = "A"
            >
            <div v-for="(image, idx) in tour.images" :key="idx">
              <b-carousel-slide img-blank >
                <b-img :src="'data:image/jpeg;base64,'+image.photo"  fluid alt="Main image Tour"></b-img>
              </b-carousel-slide>
            </div>
          </b-carousel>
        </b-row>
        <b-row class="justify-content-md-center mt-3">
          <b-col cols=6>
            <h3><strong>Informacion general</strong></h3>
            Descripcion: {{tour.description}} <br>
            <b-icon-clock-history></b-icon-clock-history>
            Duración : {{ tour.duration }} 
            <br>
            <b-icon-list-check></b-icon-list-check>
            Categoria : {{ tour.category }} <br>
            <b-icon-calendar></b-icon-calendar>
            Fecha: {{ new Date(tour.date).toLocaleString().split(' ')[0]}} 
            <br>
            <b-icon-people-fill></b-icon-people-fill>
            Cupos: {{ tour.quota }} <br>
            <b-icon-check-circle></b-icon-check-circle>
            Incluye: {{ tour.includes }} <br>
            <b-icon-x-circle></b-icon-x-circle>
            No incluye: {{ tour.notIncludes }} <br>
          </b-col>
          <b-col cols=1>
          </b-col>
          <b-col cols=4 align-self="center">
            <b-card>
              <b-row class="justify-content-md-center">
                Desde                    
                <b-badge variant="light"> <h5>${{tour.price}}</h5></b-badge>
                por persona
              </b-row>
              <b-row v-show="isLogin" class="justify-content-md-center">
                 <b-col align-self="center" sm="3">
                  <label> Tickets: </label>
                </b-col>
                <b-col sm="4">
                  <b-form-input v-model.number="tickets" type="number"></b-form-input>
                </b-col>
              </b-row>
              <b-row v-show="isLogin" class="justify-content-md-center mt-3">
                <b-col cols=8>
                  <b-button pill block variant="primary" @click="goToShop">Agregar al carrito</b-button>
                </b-col>
              </b-row>
               <b-alert
        class="alert-content" 
        dismissible
        :variant="alertvariant"
        fade
        :show="showAlert"
        @dismissed="showAlert=false"
        @dismiss-count-down="countDownChanged">
        {{messageAlert }}
        <template v-if="messageAlert == 'Por favor espere . . .'">
          <b-icon icon="clock" font-scale="2" animation="spin"></b-icon>
        </template>
      </b-alert>
            </b-card>
          </b-col>
        </b-row>
      </b-card>
    </b-card>
    <b-card bg-variant="dark" class="opacity mt-4">
      <b-row v-if="tour.comments !== 0">
        <b-col cols=12 class="text-left">
            <div class=" text-dark">
              <h4>
                <b-badge variant="primary">{{ tour.reviews }} Comentarios </b-badge> 
              </h4>
            </div>
        </b-col>
      </b-row>
      <b-card class="mt-2" v-for="(comment, idx) in tour.comments" :key="idx">
        <b-row class="justify-content-md-begin">
          <b-col cols=2>
            <b-form-rating v-model="comment.rating" variant="warning" readonly no-border></b-form-rating>
          </b-col>
        </b-row>
        <b-row class="justify-content-md-center">
          <b-col>
            {{comment.description}}
          </b-col>
        </b-row>
         <b-row class="justify-content-md-begin">
          <b-col cols=1 align-self="center">
            <b-avatar size="3em" variant="info">
              <h5>{{ comment.user.name[0]}}{{comment.user.lastName[0] }} </h5>
            </b-avatar>
          </b-col>
          <b-col cols=4 align-self="center">
            <b-row>
              <em>Valoración de </em>
            </b-row> 
            <b-row>
              <strong>{{ comment.user.name }}  {{ comment.user.lastName }} - {{ comment.user.country.name }} </strong> 
            </b-row>
          </b-col>
        </b-row>
      </b-card>
    </b-card>
  </div>
</template>

<script>

export default {
  name: 'Tour',
  data() {
    if(!this.$session.exists() ||  
       !this.$session.get('tour')){
      window.location.href = 'http://localhost:8002';
    }
    return {
      tour: this.$session.get('tour'),
      tickets: 1,
      messageAlert: "",
      showAlert: 0,
      alertvariant: "",
      secShowAlert: 5
    }

  },
  computed: {
    isLogin(){
      return this.$session.exists() && this.$session.get('user');
    }
  },
  methods: {
    countDownChanged(dismissCountDown) {
        this.showAlert = dismissCountDown
    },
    async addLike(tour){
      if(this.$session.exists() && this.$session.get('user')){
        var Id_User = this.$session.get('user').id;
        try {
          const response = await fetch('http://localhost:8001/like/tour/user', {
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
      if(this.$session.exists() && this.$session.get('user')){
        var Id_User = this.$session.get('user').id;
        try {
          const response = await fetch('http://localhost:8001/like/tour/user', {
          method: 'DELETE',
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            tour: { id : tour.id },
            user: { id : Id_User }
          })
        });
        console.log(await response.json());
        tour.liked = false;
        } catch (err) {
          this.messageAlert = "El servidor no responde";
          this.alertvariant = "danger";
          this.showAlert = this.secShowAlert;
        }
      }
    },
    returnIndex(){
      if(this.$session.exists()){
        this.$session.remove('tour');
      }
      window.location.href = 'http://localhost:8002';
    },
    goToShop(){
      if(this.$session.exists() && this.$session.get("user")) {
        this.$session.remove('tour');
        window.location.href = 'http://localhost:8002/shop';
      } else {
        this.messageAlert = "Para reservar debe iniciar sesion";
        this.alertvariant = "danger";
        this.showAlert = this.secShowAlert;
      }
    },
  }
}

</script>

<style scoped>
#result-search{
  margin-top: 5px;
}

.opacity{
  opacity: 0.95;
}

.alert-content{
  position:absolute; z-index:1;
  margin-top: 1%;
}

#carousel{
  background-color: #35383E;
}

</style>
