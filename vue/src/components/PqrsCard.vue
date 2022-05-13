<template>
  <div class="card">
    <div class="card-header d-flex justify-content-between">
      <h6>
        Created by:
        <slot name="creator"></slot>
      </h6>
      <h6>
        Status:
        <slot name="status"></slot>
      </h6>
    </div>
    <div class="card-body">
      <h5 class="card-title">
        <slot></slot>
      </h5>
      <p class="card-text">
        <slot name="description"></slot>
      </p>
    </div>
    <div class="card-footer">
      Created on
      <slot name="creation-date"></slot>
    </div>
  </div>
</template>
