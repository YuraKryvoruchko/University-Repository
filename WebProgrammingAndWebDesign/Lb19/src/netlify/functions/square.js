exports.handler = async (event) => {
    try {
        const body = JSON.parse(event.body);
        const number = body.number;

        if (typeof number !== "number") {
            return {
                statusCode: 400,
                body: JSON.stringify({ error: "Invalid number" })
            };
        }

        return {
            statusCode: 200,
            body: JSON.stringify({
                number,
                square: number * number
            })
        };
    } catch {
        return {
            statusCode: 500,
            body: JSON.stringify({ error: "Server error" })
        };
    }
};