<template>
  <main>
    <PqrsCard>
      Hey
      <template #creator>Me</template>
      <template #creation-date>{{ new Date() }}</template>
      <template #status>Completed</template>
      <template #description>Ayo</template>
    </PqrsCard>
  </main>
</template>

<script setup>
import PqrsCard from "@/components/PqrsCard.vue";
</script>
