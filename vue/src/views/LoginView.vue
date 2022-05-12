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
import { useAxios } from "@vueuse/integrations/useAxios";
import { instance } from "@/utils/axiosInstance";
import { ref } from "vue";
import qs from "qs";
import { ACCESS_TOKEN, REFRESH_TOKEN, useAuthStore } from "../stores/authStore";

const email = ref("");
const password = ref("");

const handleSubmit = async () => {
  const auth = useAuthStore();

  const queryString = qs.stringify({
    email: email.value,
    password: password.value,
  });

  const { response, isFinished } = await useAxios(
    "/auth/login",
    {
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      data: queryString,
      method: "POST",
    },
    instance,
    { immediate: true }
  );

  if (isFinished) {
    const { headers, status } = response.value;

    if (status === 200) {
      auth.setAccessToken(headers[ACCESS_TOKEN]);
      auth.setRefreshToken(headers[REFRESH_TOKEN]);
    }
  }
};
</script>

<style scoped>
main {
  height: 100vh;
}
</style>
