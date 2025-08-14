document.addEventListener("DOMContentLoaded", function () {
  const urlParams = new URLSearchParams(window.location.search);
  const gameId = urlParams.get("id");

  if (!gameId) {
    console.error("Game ID not found in URL");
    return;
  }

  fetch(`http:a5b2bc51e68294bc5a1899f2918e4b4d-1431463692.us-east-1.elb.amazonaws.com/api/v1/game/findById/${gameId}`)
    .then((res) => res.json())
    .then((data) => {
      const game = data.payload;
      document.querySelector(".page-heading h3").textContent = game.name;
      document.querySelector(".page-heading .breadcrumb").innerHTML = `
        <a href="index.html">Home</a> > 
        <a href="#">Shop</a> > 
        ${game.category}
      `;
      document.querySelector(".left-image img").src = game.imageUrl;
      document.querySelector(".left-image img").alt = game.name;
      document.querySelector(".single-product h4").textContent = game.name;
      document.querySelector(
        ".single-product .price"
      ).innerHTML = `$${game.price.toFixed(2)}`;
      document.querySelector(".single-product p").textContent =
        game.description;
      const ul = document.querySelector(".single-product ul");
      ul.innerHTML = `
        <li><span>Game ID:</span> ${game.id}</li>
        <li><span>Genre:</span> <a href="#">${game.category}</a></li>
        <li><span>Release Date:</span> ${new Date(
          game.releaseDate
        ).toDateString()}</li>
      `;
    })
    .catch((error) => {
      console.error("Failed to load game details:", error);
    });
});
