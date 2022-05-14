<template>
  <main class="d-flex justify-content-center align-items-center">
    <TheLoginForm
      v-model:email="email"
      v-model:password="password"
      @submit="handleSubmit"
    />
  </main>
</template>

<script lang="ts" setup>
import TheLoginForm from "@/components/TheLoginForm.vue";
import { ref } from "vue";
import { useAuthStore } from "@/stores/authStore";
import { useRouter } from "vue-router";
import { authenticateUser } from "@/services/auth";

const email = ref("");
const password = ref("");

const auth = useAuthStore();
const router = useRouter();

const handleSubmit = async () => {
  try {
    const { accessToken, refreshToken } = await authenticateUser(
      email.value,
      password.value
    );

    auth.setAccessToken(accessToken);
    auth.setRefreshToken(refreshToken);

    await router.replace({ name: "home" });
  } catch (e) {
    console.error(e);
  }
};
</script>

<style scoped>
main {
  height: 100vh;
}
</style>
