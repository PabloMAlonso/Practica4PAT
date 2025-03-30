let shows = [];
let n = 3;
document.addEventListener("DOMContentLoaded", function() {
  init();
});

function getRandomInt(max) {
  return Math.floor(Math.random() * max) + 1;
}

function win(result) {
  document.getElementById("rating1").textContent = shows[0];
  document.getElementById("rating2").textContent = shows[1];
  let m;
  if (shows[0] === shows[1]) {
    m = 2;
    rating1.style.color = "green";
    title1.style.color = "green";
    rating2.style.color = "green";
    title2.style.color = "green";
  } else if (shows[0] > shows[1]) {
    m = 1;
    rating1.style.color = "green";
    title1.style.color = "green";
    rating2.style.color = "red";
    title2.style.color = "red";
  } else {
    m = 0;
    rating1.style.color = "red";
    rating2.style.color = "green";
    title1.style.color = "red";
    title2.style.color = "green";
  }

  setTimeout(() => {
    if (m === result || m === 2) {
      if (confirm("You win! Want to play again?")) {
        init();
      }
    } else {
      if (confirm("You lose! Want to play again?")) {
        init();
      }
    }
  }, 100);
}

function init() {
  document.getElementById("rating1").style.color = "black";
  document.getElementById("rating2").style.color = "black";
  document.getElementById("title1").style.color = "black";
  document.getElementById("title2").style.color = "black";
  shows = [];
  let x = 0;
  for (let i = 1; i < 3; i++) {
    let ant = x;
    x = getRandomInt(n);
    while (x === ant) {
      x = getRandomInt(n);
    }
    fetch('http://localhost:8080/HigherOrLower/' + x)
      .then(response => response.json())
      .then(data => {
        let { image, name, rating, summary, genres } = data;
        shows.push(rating);
        document.getElementById(`thumbnail`+i).src = image;
        document.getElementById(`title` + i).textContent = name;
        document.getElementById(`description` + i).innerHTML = summary;
        document.getElementById(`rating` + i).textContent = "?";
        document.getElementById(`category` + i).textContent = genres ? genres.join(', ') : 'N/A';
      })
      .catch(error => {
        console.error("Error al obtener los datos", error);
        init();
      });
  }
}

function Report() {
  const showName = prompt("Enter the name of the show:");
  if (showName) {
    fetch(
      `http://localhost:8080/HigherOrLower/Delete/${encodeURIComponent(showName)}`,
      {
        method: 'DELETE'
      }
    )
    .then(response => response.json())
    .then(data => {
      alert(`${data.name} reported successfully`);
    })
    .catch(error => {
      console.error('Error:', error);
      alert("Failed to report. Please try again.");
    });
  }
}

function createShow() {
  const name = prompt("Enter the name of the show:");
  const image = prompt("Enter the image URL of the show:");
  const rating = parseFloat(prompt("Enter the rating of the show:"));
  const summary = prompt("Enter the summary of the show:");
  const genres = prompt("Enter the genres of the show (comma separated):").split(',');
  const newShow = {
    id: n+1,
    name: name,
    image: image,
    rating: rating,
    summary: summary,
    genres: genres
  };
  fetch('http://localhost:8080/HigherOrLower',
  {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(newShow)
  })
  .then(response => response.json())
  .then(data => {
    alert(`${data.name} created successfully`);
    n=n+1;
  })
  .catch(error => {
    console.error('Error:', error);
    alert("Failed to create the show. Please try again.");
  });
}
function updateShow() {
  const showName = prompt("Enter the name of the show to update:");
  if (!showName) return;
  const updateField = prompt("What do you want to update? (name, image, rating, summary, genres)");
  if (!updateField) return;
  let newValue;
  switch (updateField.toLowerCase()) {
    case "name":
      newValue = prompt("Enter the new name:");
      if (newValue) {
        fetch(`http://localhost:8080/HigherOrLower/UpdateName/${encodeURIComponent(showName)}/${encodeURIComponent(newValue)}`, {
          method: 'PUT'
        })
        .then(response => response.json())
        .then(data => {
          alert(`${data.name} updated successfully`);
        })
        .catch(error => {
          console.error('Error:', error);
          alert("Failed to update the show. Please try again.");
        });
      }
      break;
    case "image":
      newValue = prompt("Enter the new image URL:");
      if (newValue) {
        fetch(`http://localhost:8080/HigherOrLower/UpdateImage/${encodeURIComponent(showName)}/${encodeURIComponent(newValue)}`, {
          method: 'PUT'
        })
        .then(response => response.json())
        .then(data => {
          alert(`${data.name} updated successfully`);
        })
        .catch(error => {
          console.error('Error:', error);
          alert("Failed to update the show. Please try again.");
        });
      }
      break;
    case "rating":
      newValue = parseFloat(prompt("Enter the new rating:"));
      if (!isNaN(newValue)) {
        fetch(`http://localhost:8080/HigherOrLower/UpdateRating/${encodeURIComponent(showName)}/${newValue}`, {
          method: 'PUT'
        })
        .then(response => response.json())
        .then(data => {
          alert(`${data.name} updated successfully`);
        })
        .catch(error => {
          console.error('Error:', error);
          alert("Failed to update the show. Please try again.");
        });
      }
      break;
    case "summary":
      newValue = prompt("Enter the new summary:");
      if (newValue) {
        fetch(`http://localhost:8080/HigherOrLower/UpdateSummary/${encodeURIComponent(showName)}/${encodeURIComponent(newValue)}`, {
          method: 'PUT'
        })
        .then(response => response.json())
        .then(data => {
          alert(`${data.name} updated successfully`);
        })
        .catch(error => {
          console.error('Error:', error);
          alert("Failed to update the show. Please try again.");
        });
      }
      break;
    case "genres":
      newValue = prompt("Enter the new genres (comma separated):");
      if (newValue) {
        const genresArray = newValue.replace(/\s+/g, '');
        fetch(`http://localhost:8080/HigherOrLower/UpdateGenres/${encodeURIComponent(showName)}/${encodeURIComponent(genresArray)}`,
        {
          method: 'PUT',

        })
        .then(response => response.json())
        .then(data => {
          alert(`${data.name} updated successfully`);
        })
        .catch(error => {
          console.error('Error:', error);
          alert("Failed to update the show. Please try again.");
        });
      }
      break;
    default:
      alert("Invalid field. Please try again.");
  }
}