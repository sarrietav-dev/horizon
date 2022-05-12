<template>
  <main class="d-flex justify-content-center align-items-center">
    <TheLoginForm
      v-model:email="email"
      v-model:password="password"
      @submit="handleSubmit"
    />
  </main>
</template>

<script setup>
import TheLoginForm from "@/components/TheLoginForm.vue";
import { ref } from "vue";
import qs from "qs";
import { ACCESS_TOKEN, REFRESH_TOKEN, useAuthStore } from "../stores/authStore";
import { useRouter } from "vue-router";
import axios from "axios";

const email = ref("");
const password = ref("");

const auth = useAuthStore();
const router = useRouter();

const handleSubmit = async () => {
  const queryString = qs.stringify({
    email: email.value,
    password: password.value,
  });

  const response = await axios.post("/api/auth/login", queryString, {
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
  });

  const { headers, status } = response;

  if (status === 200) {
    auth.setAccessToken(headers[ACCESS_TOKEN]);
    auth.setRefreshToken(headers[REFRESH_TOKEN]);

    await router.replace({ name: "home" });
  }
};
</script>

<style scoped>
main {
  height: 100vh;
}
</style>
