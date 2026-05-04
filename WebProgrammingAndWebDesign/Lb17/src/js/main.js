document.body.style.margin = "0";
document.body.style.display = "flex";
document.body.style.justifyContent = "center";
document.body.style.alignItems = "center";
document.body.style.height = "100vh";
document.body.style.backgroundColor = "#111";

const calc = document.createElement("div");
document.body.appendChild(calc);

Object.assign(calc.style, {
    width: "500px",
    background: "#222",
    padding: "1%",
    borderRadius: "10px",
    boxShadow: "0 0 10px rgba(0,0,0,0.5)"
});

const display = document.createElement("input");
calc.appendChild(display);

Object.assign(display.style, {
    width: "100%",
    height: "50px",
    fontSize: "2rem",
    marginBottom: "10px",
    textAlign: "right",
    padding: "5px",
    boxSizing: "border-box"
});

const buttonsContainer = document.createElement("div");
calc.appendChild(buttonsContainer);

Object.assign(buttonsContainer.style, {
    display: "grid",
    gridTemplateColumns: "repeat(4, 1fr)",
    gap: "10px"
});

const buttons = [
    "7","8","9","/",
    "4","5","6","*",
    "1","2","3","-",
    "0",".","=","+"
];

buttons.forEach(text => {
    const btn = document.createElement("button");
    btn.innerText = text;
    buttonsContainer.appendChild(btn);

    Object.assign(btn.style, {
        height: "80px",
        fontSize: "18px",
        border: "none",
        borderRadius: "5px",
        cursor: "pointer"
    });

    btn.onclick = () => handleClick(text);
});

function handleClick(value) {
    if (value === "=") {
        try {
            display.value = eval(display.value);
        } catch {
            display.value = "Error";
        }
    } else {
        display.value += value;
    }
}

function adapt() {
    if (window.innerWidth < 550) {
        calc.style.width = "90%";
    } else {
        calc.style.width = "500px";
    }
}

window.addEventListener("resize", adapt);
adapt();