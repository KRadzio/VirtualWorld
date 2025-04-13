#include "World.h"

int main()
{
    World w;
    int size;
    cout << "Podaj rozmiar" << endl;
    cin >> size;
    w.setSize(size);
    w.setOrganisms();
    char playerInput;
    system("clear");
    while (w.isHumanAlive())
    {
        printf("%c[%d;%df", 0x1B, 1, 1);
        cout << "Symulator" << endl;
        cout << "Radoslaw Piotrowicz 193251" << endl;
        cout << "Strzalki poruszanie sie" << endl;
        cout << "e specjalna umiejetnosc" << endl;
        cout << "p nie przemieszczaj sie" << endl;
        cout << "Tura: " << w.getTurn() << endl;
        cout << "k koniec symulacji" << endl;
        w.sortOrganisms();
        w.drawWorld();
        w.drawEvents();
        w.passTurn();
        w.removeOrganisms();
        system("clear");
    }
    w.clearWorld();
    w.clearEvents();
    system("clear");
    return 0;
}