document.addEventListener("DOMContentLoaded", function () {
  const container = document.getElementById("product-list");

  fetch("http:a5b2bc51e68294bc5a1899f2918e4b4d-1431463692.us-east-1.elb.amazonaws.com/api/v1/game")
    .then((response) => response.json())
    .then((res) => {
      const games = res.payload;
      container.innerHTML = "";

      games.forEach((game) => {
        const category = game.category.toLowerCase();

        let filterClass = "";
        if (category === "adventure") filterClass = "adv";
        else if (category === "shooter") filterClass = "sht";
        else if (category === "strategy") filterClass = "str";
        else if (category === "racing") filterClass = "rac";

        const card = document.createElement("div");
        card.className = `col-lg-3 col-md-6 align-self-center mb-30 trending-items ${filterClass}`;

        card.innerHTML = `
          <div class="item">
            <div class="thumb">
              <a href="product-details.html?id=${game.id}">
                <img src="${game.imageUrl}" alt="${
          game.name
        }" style="width: 100%; height: 200px; object-fit: cover;">
              </a>
              <span class="price">$${game.price.toFixed(2)}</span>
            </div>
            <div class="down-content">
              <span class="category">${game.category}</span>
              <h4>${game.name}</h4>
              <a href="product-details.html?id=${game.id}">
                <i class="fa fa-shopping-bag"></i>
              </a>
            </div>
          </div>
        `;

        container.appendChild(card);
      });
        
      imagesLoaded(container, function () {
        const $grid = $(".trending-box").isotope({
          itemSelector: ".trending-items",
          layoutMode: "fitRows",
        });
        $(".trending-filter a").on("click", function (e) {
          e.preventDefault();
          const filter = $(this).attr("data-filter");
          $grid.isotope({ filter: filter });

          $(".trending-filter a").removeClass("is_active");
          $(this).addClass("is_active");
        });
        window.addEventListener("resize", () => {
          $grid.isotope("layout");
        });
      });
    })
    .catch((error) => {
      console.error("Error loading game data:", error);
    });
});
