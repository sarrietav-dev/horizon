<template>
  <div class="card">
    <div class="card-body">
      <h2 class="text-center my-3">Iniciar Sesion</h2>
      <form class="container" method="POST" @submit.prevent="handleSubmit">
        <div class="row my-2">
          <div class="col">
            <input
              v-model="emailModel"
              aria-label="Email"
              class="form-control"
              placeholder="Email"
              type="email"
            />
          </div>
        </div>
        <div class="row my-2">
          <div class="col">
            <input
              v-model="passwordModel"
              aria-label="Contraseña"
              class="form-control"
              placeholder="Contraseña"
              type="password"
            />
          </div>
        </div>
        <div class="d-flex justify-content-center align-items-center">
          <button class="btn btn-primary" type="submit">Iniciar Sesion</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { computed } from "vue";

const props = defineProps<{ email: string; password: string }>();
const emit = defineEmits(["submit", "update:email", "update:password"]);

const handleSubmit = () => emit("submit");

const emailModel = computed({
  get() {
    return props.email;
  },
  set(value) {
    emit("update:email", value);
  },
});

const passwordModel = computed({
  get() {
    return props.password;
  },
  set(value) {
    emit("update:password", value);
  },
});
</script>
