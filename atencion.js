const contactWidget = document.createElement("aside");
contactWidget.className = "contact-widget";
contactWidget.innerHTML = `
  <div class="contact-panel" id="contactPanel" aria-labelledby="contactTitle" hidden>
    <div class="contact-panel-header">
      <div>
        <span class="contact-eyebrow">BYTHEPHONE</span>
        <h2 id="contactTitle">¿Cómo podemos ayudarte?</h2>
      </div>
      <button class="contact-close" type="button" aria-label="Cerrar opciones de contacto">×</button>
    </div>
    <a class="contact-option" href="https://wa.me/573018951862" target="_blank" rel="noopener noreferrer">
      <span class="contact-option-icon whatsapp-icon" aria-hidden="true">W</span>
      <span><strong>Chat de WhatsApp</strong><small>Escríbenos directamente</small></span>
      <span class="contact-arrow" aria-hidden="true">↗</span>
    </a>
    <a class="contact-option" href="tel:+573018951862">
      <span class="contact-option-icon phone-icon" aria-hidden="true">☎</span>
      <span><strong>Atención telefónica</strong><small>+57 301 895 1862</small></span>
      <span class="contact-arrow" aria-hidden="true">↗</span>
    </a>
    <a class="contact-option" href="preguntas.html">
      <span class="contact-option-icon faq-icon" aria-hidden="true">?</span>
      <span><strong>Preguntas frecuentes</strong><small>Encuentra una respuesta</small></span>
      <span class="contact-arrow" aria-hidden="true">↗</span>
    </a>
  </div>
  <button
    class="contact-toggle"
    type="button"
    aria-label="Abrir opciones de atención"
    aria-expanded="false"
    aria-controls="contactPanel"
  >
    <span class="contact-toggle-icon" aria-hidden="true">☏</span>
    <span>Atención</span>
  </button>
`;
document.body.append(contactWidget);

const contactToggle = contactWidget.querySelector(".contact-toggle");
const contactPanel = contactWidget.querySelector(".contact-panel");
const contactClose = contactWidget.querySelector(".contact-close");

function setContactPanelOpen(isOpen) {
  contactPanel.hidden = !isOpen;
  contactToggle.setAttribute("aria-expanded", String(isOpen));
  contactToggle.setAttribute(
    "aria-label",
    isOpen ? "Cerrar opciones de atención" : "Abrir opciones de atención"
  );
  if (isOpen) contactClose.focus();
}

contactToggle.addEventListener("click", () => {
  setContactPanelOpen(contactPanel.hidden);
});

contactClose.addEventListener("click", () => {
  setContactPanelOpen(false);
  contactToggle.focus();
});

document.addEventListener("keydown", (event) => {
  if (event.key === "Escape" && !contactPanel.hidden) {
    setContactPanelOpen(false);
    contactToggle.focus();
  }
});

document.addEventListener("click", (event) => {
  const contactTrigger = event.target.closest("[data-open-contact], #openContact");
  if (!contactPanel.hidden && !contactWidget.contains(event.target) && !contactTrigger) {
    setContactPanelOpen(false);
  }
});
